package com.maple.freemarker;

import com.maple.task.TaskNode;
import com.maple.wapper.DatabaseWapper;
import com.maple.wapper.TableWapper;
import freemarker.template.Template;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CodeService {

	private FreeMarkerConfig freeMarkerConfig;
	private Map<String, List<TaskNode>> taskNodeMap;

	public CodeService() {
	}

	public List<CodeFile> gencode(DatabaseWapper database, String taskName, Map<String, Object> config)
			throws Exception {
		List<CodeFile> filelist = new ArrayList<CodeFile>();
		for (String tableName : database.getTableNames()) {
			TableWapper table = database.getTable(tableName);
			filelist.addAll(this.gencode(table, taskName, config));
		}
		return filelist;
	}

	public List<CodeFile> gencode(TableWapper table, String taskName, Map<String, Object> config) throws Exception {
		List<TaskNode> nodelist = this.taskNodeMap.get(taskName);
		if (nodelist == null) {
			throw new RuntimeException("任务名称不存在！");
		}
		List<CodeFile> filelist = new ArrayList<CodeFile>();
		for (TaskNode taskNode : nodelist) {
			filelist.addAll(taskNode.execute(table, config));
		}
		return filelist;
	}

	public String executeTemplate(String name, Object context) throws Exception {
		StringWriter out = new StringWriter();
		Template template = this.getTemplate(name);
		template.process(context, out);
		return out.toString();
	}

	protected Template getTemplate(String name) throws Exception {
		return this.freeMarkerConfig.getConfiguration().getTemplate(name);
	}

	public void setFreeMarkerConfig(FreeMarkerConfig freeMarkerConfig) {
		this.freeMarkerConfig = freeMarkerConfig;
	}

	public void setTaskNodeMap(Map<String, List<TaskNode>> taskNodeMap) {
		this.taskNodeMap = taskNodeMap;
	}
}
