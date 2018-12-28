package com.tec.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestDemo {
	public static void main(String[] args) {

		Filter f1 = new Filter();
		f1.setUsername("moer");

		Filter f2 = new Filter();
		f2.setUsername("bing");
		f2.setPassword("123a");

		System.out.println(query(f1));
		System.out.println(query(f2));
	}

	/**
	 * 传入Filter对象,返回对应的sql查询语句
	 * @param f
	 * @return
	 */
	private static String query(Filter f) {
		// 用来拼接字符串
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ");
		String tableName = null;
		// 获得Filter类的类类型(加载类)
		Class<? extends Filter> filter = f.getClass();

		// 是否存在我们需要找的注解
		boolean isExist = filter.isAnnotationPresent(Table.class);
		if (!isExist) {
			return null;
		}
		else {
			// 拿到注解的实例
			Table t = (Table) filter.getAnnotation(Table.class);
			// 拿到表名
			tableName = t.value();
		}

		sb.append(tableName).append(" where 1=1");

		// 取出所有的字段
		Field[] fields = filter.getDeclaredFields();
		for (Field field : fields) {
			// 处理每个字段对应的sql
			// 先拿到字段名和字段值再拼装
			boolean ismExist = field.isAnnotationPresent(Columns.class);
			if (!ismExist) {
				continue;
			}

			// 拿到字段注解的实例
			Columns col = (Columns) field.getAnnotation(Columns.class);
			// 拿到sql的列名
			String columnValue = col.value();

			// 拿到字段名
			String fieldName = field.getName();
			// 拼接出每个字段的get方法(例如: getUsername)
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			String fieldValue = null;

			try {
				// 通过反射获得get方法
				Method getMethod = filter.getMethod(getMethodName);
				// 通过反射执行get方法拿到字段的值
				fieldValue = (String) getMethod.invoke(f);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (fieldValue != null) {
				// 拼装sql
				sb.append(" and ").append(columnValue).append("='").append(fieldValue).append("'");
			}

		}

		return sb.toString();
	}
}