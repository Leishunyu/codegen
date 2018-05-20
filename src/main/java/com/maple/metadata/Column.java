package com.maple.metadata;


public class Column {

	// 字段名称
	private String name;
	// 字段序号
	private Long ordinal;
	// 字段类型
	private String type;
	// 字段类型(全)
	private String dataType;
	// 默认值
	private String defValue;
	// 是否可为空
	private String nullable;
	// 字段长度(字符串)
	private Long length;
	// 字段长度(数值)
	private Long precision;
	// 小数长度(数值)
	private Long scale;
	// PRI：主键
	private String key;
	// auto_increment标识为自增长
	private String extra;
	// 字段注释
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Long ordinal) {
		this.ordinal = ordinal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public Long getPrecision() {
		return precision;
	}

	public void setPrecision(Long precision) {
		this.precision = precision;
	}

	public Long getScale() {
		return scale;
	}

	public void setScale(Long scale) {
		this.scale = scale;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
