package org.smart4j.charpter2.util;

/**
 * 2016-01-21
 * 
 * @author JTMRengl ת�����͹�����
 */
public class CastUtil {

	/**
	 * ת����String
	 */
	public static String castString(Object obj) {
		return castString(obj, "");
	}

	/**
	 * ת����String
	 */
	public static String castString(Object obj, String defaulValue) {

		return obj != null ? String.valueOf(obj) : defaulValue;
	}

	/**
	 * ת����int
	 */
	public static int castInt(Object obj) {

		return castInt(obj, 0);
	}

	/**
	 * ת����int
	 */
	public static int castInt(Object obj, int defaultValue) {
		int value = defaultValue;

		if (obj != null) {
			String strValue = castString(obj);

			// �ж�һ��obj����ȥ�ո�֮���ǲ��ǿ�
			if (StringUtil.isNotEmpty(strValue)) {
				value = Integer.parseInt(strValue);
			}

		}

		return value;
	}

	/**
	 * ת����double
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
	 * ת����long
	 */
	public static Long castLong(Object obj) {

		return castLong(obj, 0);
	}

	/**
	 * ת����long
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
	 * ת����boolean
	 */
	public static boolean castBoolean(Object obj) {

		return castBoolean(obj, false);
	}

	/**
	 * ת����boolean
	 */
	public static boolean castBoolean(Object obj, boolean defaultValue) {
		boolean value = defaultValue;

		if (obj != null) {
			value = Boolean.parseBoolean(castString(obj));
		}

		return value;
	}
}
