package org.smart4j.charpter2.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 2016-01-21
 * @author JTMRengl
 * �ַ���������
 */
public class StringUtil {

	/**
	 * �ж��ַ����ǿ�
	 */
	public static boolean isNotEmpty(String str){
		//ǰ��ȥ�ո�
		if(str != null){
			str = str.trim();
		}
		
		return StringUtils.isNotEmpty(str);
	}
	/**
	 * �ж��ַ����ǿ�
	 */
	public static boolean isEmpty(String str){
		
		return !isNotEmpty(str);
	}
}
