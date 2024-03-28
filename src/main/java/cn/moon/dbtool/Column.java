package cn.moon.dbtool;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Column {

	String label;
	String name;

	/**
	 * 数据库类型
	 * SQL type from java.sql.Types
	 */
	int type;

	String typeName;

	/**
	 * java类型
	 */
	String className;





}
