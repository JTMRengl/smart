package org.smart4j.charpter2.util;

/**
 * 2016-01-21
 * 
 * @author JTMRengl 转换类型工具类
 */
public class CastUtil {

	/**
	 * 转换成String
	 */
	public static String castString(Object obj) {
		return castString(obj, "");
	}

	/**
	 * 转换成String
	 */
	public static String castString(Object obj, String defaulValue) {

		return obj != null ? String.valueOf(obj) : defaulValue;
	}

	/**
	 * 转换成int
	 */
	public static int castInt(Object obj) {

		return castInt(obj, 0);
	}

	/**
	 * 转换成int
	 */
	public static int castInt(Object obj, int defaultValue) {
		int value = defaultValue;

		if (obj != null) {
			String strValue = castString(obj);

			// 判断一下obj对象去空格之后是不是空
			if (StringUtil.isNotEmpty(strValue)) {
				value = Integer.parseInt(strValue);
			}

		}

		return value;
	}

	/**
	 * 转换成double
	 */
	public static Double castDouble(Object obj) {

		return castDouble(obj, 0);
	}

	public static Double castDouble(Object obj, double defaultValue) {
		Double value = defaultValue;

		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				value = Double.parseDouble(strValue);
			}
		}

		return value;
	}

	/**
	 * 转换成long
	 */
	public static Long castLong(Object obj) {

		return castLong(obj, 0);
	}

	/**
	 * 转换成long
	 */
	public static Long castLong(Object obj, long defaultValue) {
		long value = defaultValue;

		if (obj != null) {
			String strValue = castString(obj);

			if (StringUtil.isNotEmpty(strValue)) {
				value = Long.parseLong(strValue);
			}
		}

		return value;
	}

	/**
	 * 转换成boolean
	 */
	public static boolean castBoolean(Object obj) {

		return castBoolean(obj, false);
	}

	/**
	 * 转换成boolean
	 */
	public static boolean castBoolean(Object obj, boolean defaultValue) {
		boolean value = defaultValue;

		if (obj != null) {
			value = Boolean.parseBoolean(castString(obj));
		}

		return value;
	}
}
