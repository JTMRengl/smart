package org.smart4j.charpter2.util;

import java.util.Collection;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;

public class CollectionUtil {

	/**
	 * ÅÐ¶ÏCollectionÊÇ·ñÎª¿Õ
	 */
	public static boolean isEmpty(Collection<?> collection){
		return CollectionUtils.isEmpty(collection);
	}
	/**
	 * ÅÐ¶ÏCollectionÊÇ·ñ·Ç¿Õ
	 */
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	/**
	 * ÅÐ¶ÏMapÊÇ·ñÎª¿Õ
	 */
	public static boolean isEmpty(Map<?, ?> map){
		return map.isEmpty();
	}
	/**
	 * ÅÐ¶ÏMapÊÇ·ñ·Ç¿Õ
	 */
	public static boolean isNotEmpty(Map<?, ?> map){
		return !isEmpty(map);
	}
}
