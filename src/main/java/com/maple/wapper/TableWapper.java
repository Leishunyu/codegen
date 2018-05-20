package com.maple.wapper;


import com.maple.metadata.Column;
import com.maple.metadata.Table;
import com.maple.metadata.TypeConverter;
import com.maple.utils.NameUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableWapper implements Wapper<Table> {

	private Table						table;
	private ColumnWapper				primaryKey;
	private Map<String, ColumnWapper>	columns	= new LinkedHashMap<String, ColumnWapper>();

	private String						javaName;
	//是否有删除标识
	private boolean						deletedEntity;
	//是否需要乐观锁
	private boolean						versionEntity;

	public TableWapper(Table table, TypeConverter typeConverter) {
		super();
		this.table = table;
		if (table.getPrimaryKey() != null) {
			this.primaryKey = new ColumnWapper(table.getPrimaryKey(), typeConverter);
		}
		for (Map.Entry<String, Column> entry : table.getColumns().entrySet()) {
			this.columns.put(entry.getKey(), new ColumnWapper(entry.getValue(), typeConverter));
			if (entry.getKey().equals("DELETED")) {
				this.deletedEntity = true;
			}
			if (entry.getKey().equalsIgnoreCase("version")) {
				this.versionEntity = true;
			}
		}
		// 根据规则生成实体名称
		this.javaName = NameUtils.tableNameToClassName(this.getName());

	}

	@Override
	public Table getTarget() {
		return this.table;
	}

	public String getCatalog() {
		return table.getCatalog();
	}

	public String getSchema() {
		return table.getSchema();
	}

	public String getName() {
		return table.getName();
	}

	public String getComment() {
		return table.getComment();
	}

	public Map<String, ColumnWapper> getColumnMap() {
		return this.columns;
	}

	public Collection<ColumnWapper> getColumns() {
		return this.columns.values();
	}

	public ColumnWapper getColumn(String name) {
		return this.columns.get(name);
	}

	public ColumnWapper getPrimaryKey() {
		return this.primaryKey;
	}

	public String getJavaName() {
		return this.javaName;
	}

	public String getJavaNameL() {
		return NameUtils.firstLowerCase(this.javaName);
	}

	public boolean isDeletedEntity() {
		return deletedEntity;
	}

	public boolean isVersionEntity() {
		return versionEntity;
	}

	public void setVersionEntity(boolean versionEntity) {
		this.versionEntity = versionEntity;
	}
	
}
