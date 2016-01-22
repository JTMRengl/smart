package org.smart4j.charpter2.util;

import java.util.Collection;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;

public class CollectionUtil {

	/**
	 * �ж�Collection�Ƿ�Ϊ��
	 */
	public static boolean isEmpty(Collection<?> collection){
		return CollectionUtils.isEmpty(collection);
	}
	/**
	 * �ж�Collection�Ƿ�ǿ�
	 */
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	/**
	 * �ж�Map�Ƿ�Ϊ��
	 */
	public static boolean isEmpty(Map<?, ?> map){
		return map.isEmpty();
	}
	/**
	 * �ж�Map�Ƿ�ǿ�
	 */
	public static boolean isNotEmpty(Map<?, ?> map){
		return !isEmpty(map);
	}
}
