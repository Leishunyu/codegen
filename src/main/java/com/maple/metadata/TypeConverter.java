package com.maple.metadata;

import java.util.*;

public class TypeConverter {

	private List<Types> typesMap = new ArrayList<Types>();
	//没有配置的对象类型
	private Types UNKOWN = new Types("unkown", "unkown", new HashSet<String>());

	public TypeConverter(Map<String, String> settings) {
		for (Map.Entry<String, String> entry : settings.entrySet()) {
			String[] array = entry.getKey().split("-");
			String[] dbTypes = entry.getValue().split(",");
			this.typesMap.add(new Types(array[0], array[1], new HashSet<String>(Arrays.asList(dbTypes))));
		}
	}

	public String toJavaType(Column column) {
		Types types = this.matcher(column);
		return types.getJavaType();
	}

	public String toJdbcType(Column column) {
		Types types = this.matcher(column);
		return types.getJdbcType();
	}

	private Types matcher(Column column) {
		for (Types types : typesMap) {
			if (types.matcher(column)) {
				return types;
			}
		}
		return UNKOWN;
	}

	private static class Types {

		private String javaType;
		private String jdbcType;
		private Set<String> dbTypes;

		public Types(String javaType, String jdbcType, Set<String> dbTypes) {
			this.javaType = javaType;
			this.jdbcType = jdbcType;
			this.dbTypes = dbTypes;
		}

		public boolean matcher(Column column) {
			if (this.dbTypes.isEmpty()) {
				return true;
			}
			return this.dbTypes.contains(column.getDataType().toUpperCase());
		}

		public String getJavaType() {
			return this.javaType;
		}

		public String getJdbcType() {
			return this.jdbcType;
		}
	}
}
