package com.maple.utils;

import org.apache.commons.lang3.StringUtils;

public class NameUtils {

	public static String tableNameToClassName(String name) {
		String[] array = name.toLowerCase().split("_");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(firstUpperCase(array[i]));
		}
		return builder.toString();
	}

	public static String columnNameToPropName(String name) {
		String[] array = name.toLowerCase().split("_");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			builder.append(firstUpperCase(array[i]));
		}
		return builder.toString();
	}

	public static String firstLowerCase(String str) {
		return StringUtils.lowerCase(str.substring(0, 1)) + str.substring(1);
	}

	public static String firstUpperCase(String str) {
		return StringUtils.upperCase(str.substring(0, 1)) + str.substring(1);
	}
}
