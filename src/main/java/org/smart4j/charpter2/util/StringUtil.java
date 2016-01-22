package org.smart4j.charpter2.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 2016-01-21
 * @author JTMRengl
 * ×Ö·û´®¹¤¾ßÀà
 */
public class StringUtil {

	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ¿Õ
	 */
	public static boolean isNotEmpty(String str){
		//Ç°ºóÈ¥¿Õ¸ñ
		if(str != null){
			str = str.trim();
		}
		
		return StringUtils.isNotEmpty(str);
	}
	/**
	 * ÅÐ¶Ï×Ö·û´®·Ç¿Õ
	 */
	public static boolean isEmpty(String str){
		
		return !isNotEmpty(str);
	}
}
