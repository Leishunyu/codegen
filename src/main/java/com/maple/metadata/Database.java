package com.maple.metadata;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Database {

	private final String catalog;
	private final String schema;

	private final Map<String, Table> tables = new HashMap<String, Table>();

	public Database(String catalog, String schema) {
		this.catalog = catalog;
		this.schema = schema;
	}

	public String getCatalog() {
		return catalog;
	}

	public String getSchema() {
		return schema;
	}

	public void addTable(Table table) {
		tables.put(table.getName().toUpperCase(Locale.ENGLISH), table);
	}

	public Table getTable(String name) {
		return tables.get(name.toUpperCase(Locale.ENGLISH));
	}

	public String[] getTableNames() {
		return tables.keySet().toArray(new String[tables.size()]);
	}
}