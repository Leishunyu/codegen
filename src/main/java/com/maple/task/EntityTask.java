package com.maple.task;

import com.maple.freemarker.CodeFile;
import com.maple.freemarker.JavaCodeFile;
import com.maple.wapper.TableWapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Component
public class EntityTask extends AbstractTaskNode{
    
    private String templateEntity = "Entity.ftl";
    private String templateMapper = "EntityMapper.ftl";
    
    @Override
    public List<CodeFile> execute(TableWapper table, Map<String, Object> config) throws Exception{
        String basepkg = (String) config.get("basepkg");
        Map<String, Object> map = this.buildContext(table, config);
        // entity
        String name = table.getJavaName() + ".java";
        String content = codeService.executeTemplate(templateEntity, map);
        CodeFile entityFile = new JavaCodeFile(basepkg + ".entity", name, content);
        // mapper
        name = table.getJavaName() + "Mapper.xml";
        content = codeService.executeTemplate(templateMapper, map);
        CodeFile mapperFile = new JavaCodeFile(basepkg + ".dao.mapper", name, content);
        return Arrays.asList(entityFile, mapperFile);
    }
}
