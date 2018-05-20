package ${config.basepkg}.entity;

/**
 * 实体对象：${table.comment}
 */
public class ${table.javaName} {

	private static final long serialVersionUID = ${serialVersionUID};

	// ~~~~实体属性
	<#list table.columns as column>
	<#--//如果需要过滤某些字段(继承公共父类等，可配置该项)-->
	<#--<#if column.view>-->
	// ${column.comment}
	private ${column.javaType} ${column.javaName};
	<#--</#if>-->
	</#list>

	<#list table.columns as column>
	<#--//如果需要过滤某些字段(继承公共父类等，可配置该项)-->
	<#--<#if column.view>-->
	//get/set

	public ${column.javaType} get${(column.javaName)?cap_first!}() {
		return this.${column.javaName};
	}

	public void set${(column.javaName)?cap_first!}(${column.javaType} ${column.javaName}) {
		this.${column.javaName} = ${column.javaName};
	}
	<#--</#if>-->
	</#list>
}
