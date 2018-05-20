package com.maple.metadata;

import com.maple.wapper.DatabaseWapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseFactory {
    
    private SqlSession sqlSession;
    
    private TypeConverter typeConverter;
    
    public List<Table> findTables(String schema, String tableName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("schema", schema);
        params.put("name", tableName);
        String statement = "database.findTables";
        return sqlSession.<Table> selectList(statement, params);
    }
    
    public List<Column> findColumns(String schema, String tableName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("schema", schema);
        params.put("name", tableName);
        String statement = "database.findColumns";
        return sqlSession.<Column> selectList(statement, params);
    }
    
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    public Database readDatabase(String catalog, String schema, String tableName) {
        Database database = new Database(catalog, schema);
        String[] names = tableName.split(",");
        for (String name : names) {
            List<Table> tables = this.findTables(schema, name);
            for (Table table : tables) {
                List<Column> columns = this.findColumns(schema, table.getName());
                for (Column column : columns) {
                    table.addColumn(column);
                }
                database.addTable(table);
            }
        }
        return database;
    }
    
    public DatabaseWapper readDatabaseWapper(String catalog, String schema, String tableName) {
        return new DatabaseWapper(this.readDatabase(catalog, schema, tableName), typeConverter);
    }
    
    public void setTypeConverter(TypeConverter typeConverter) {
        this.typeConverter = typeConverter;
    }
    
}
