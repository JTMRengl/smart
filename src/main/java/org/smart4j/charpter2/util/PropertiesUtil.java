package org.smart4j.charpter2.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2016-01-20
 * @author JTMRengl
 * �������ļ�������
 */
public final class PropertiesUtil {
	
	//��־����
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);	

	/**
	 * ��������:���������ļ�
	 * 
	 * �ؼ���
	 * 1.�����ļ����ƻ�ȡ������
	 * 2.�����ļ����������
	 * 
	 * �ؼ�����
	 * ����������-props
	 * �����ļ�����-is
	 * 
	 * �������
	 * �ļ���(����·��)-filename
	 * �������
	 * ���Զ���-props
	 * 
	 * �쳣
	 * 1.�ļ��Ҳ����쳣
	 * 2.�����ļ������쳣
	 * 3.�ر����쳣
	 * 
	 * ��־���
	 * 1.�����ļ����ش���
	 * 2.�������رմ���
	 */
	public static Properties loadProperties(String filename){
		
		Properties props = null;
		InputStream is = null;
		try {
			//�ؼ���1
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
			if(is == null){
				throw new FileNotFoundException("file is not found");
			}
			//�ؼ�2
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			LOGGER.error("load properties file failure",e);
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				LOGGER.error("close input stream failure");
			}
		}
		
		return props;
	}
	/**
	 * ��������:��ȡ�ַ�������(��ָ��Ĭ��ֵ)
	 * 
	 * �������
	 * �����ļ�����-props
	 * key
	 * Ĭ��ֵ-defaultValue
	 * 
	 * ����:�ַ���
	 * 
	 * �ؼ�������:�ж������ļ������а���key��ȡ��key��Ӧ������ֵ:ȡĬ��ֵ
	 */
	public static String getString(Properties props, String key, String defaultValue){
		String value = defaultValue;
		if(props.containsKey(key)){
			value = props.getProperty(key);
		}
		
		return value;
	}
	/**
	 * ��������:��ȡ�ַ�������(Ĭ��ֵΪ�յ��ַ���)
	 */
	public static String getString(Properties props, String key){
		
		return getString(props, key, "");
	}
	/**
	 * ��ȡ��ֵ������(Ĭ��ֵΪ0)
	 */
	public static int getInt(Properties props,String key){
		
		return getInt(props,key,0);
	}	
	/**
	 * ��ȡ��ֵ������
	 */
	public static int getInt(Properties props,String key,int defaultValue){
		int value = defaultValue;
		
		//�ж������ļ��е�key�Ƿ����key
		if(props.containsKey(key)){
			//��ת�����͹��߽��ַ�����ת����int,��ע�͵��ķ�ʽ��ȻҲ����,����Ŀ���ǽ�����ת���������ɹ�����
			//value = Integer.parseInt(key);
			value = CastUtil.castInt(props.getProperty(key));
		}
		
		return value;
	}

	/**
	 * ��ȡ����������
	 */
	public static boolean getBoolean(Properties props, String key){
		
		return  getBoolean(props,key,false);
	}
	/**
	 * ��ȡ����������
	 */
	public static boolean getBoolean(Properties props, String key,Boolean defalutValue){
		
		boolean value = defalutValue;
		if(props.containsKey(key)){
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		
		return value;
	}
}
