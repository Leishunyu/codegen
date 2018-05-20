package com.maple;

import com.maple.freemarker.CodeFile;
import com.maple.freemarker.CodeService;
import com.maple.metadata.DataBaseFactory;
import com.maple.utils.FileUtils;
import com.maple.wapper.DatabaseWapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    
    public static void main(String[] args) throws Exception {
        // 配置项
        Map<String, Object> config = new HashMap<String, Object>();
        // 基本包路 径
        config.put("basepkg", "com.maple.test");
        // 数据库schema，Oracle数据库的用户名
        String schema = "test";
        // 数据表名称，多个以逗号隔开，支持Like查询
        String tableName = "test";
        // 代码写入磁盘路径
        String rootPath = "maple-codegen/outCode";
        // 数据库catalog
        String catalog = "";
        String taskName = "java";
        // 代码生成
        String location = "classpath:applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(location);
        DataBaseFactory databaseFactory = (DataBaseFactory) context.getBean("databaseFactory");
        DatabaseWapper database = databaseFactory.readDatabaseWapper(catalog.toUpperCase(), schema.toUpperCase(), tableName.toUpperCase());
        System.out.println("Tables: " + Arrays.asList(database.getTableNames()));
        CodeService service = (CodeService) context.getBean("codeService");
        List<CodeFile> filelist = service.gencode(database, taskName, config);
        FileUtils.writeCodeFile(rootPath, filelist);
    }
}
