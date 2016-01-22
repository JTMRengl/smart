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
 * 读属性文件工具类
 */
public final class PropertiesUtil {
	
	//日志对象
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);	

	/**
	 * 功能描述:加载属性文件
	 * 
	 * 关键点
	 * 1.根据文件名称获取输入流
	 * 2.属性文件加载这个流
	 * 
	 * 关键对象
	 * 输入流对象-props
	 * 属性文件对象-is
	 * 
	 * 输入参数
	 * 文件名(包含路径)-filename
	 * 输出参数
	 * 属性对象-props
	 * 
	 * 异常
	 * 1.文件找不到异常
	 * 2.属性文件加载异常
	 * 3.关闭流异常
	 * 
	 * 日志输出
	 * 1.属性文件加载错误
	 * 2.输入流关闭错误
	 */
	public static Properties loadProperties(String filename){
		
		Properties props = null;
		InputStream is = null;
		try {
			//关键点1
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
			if(is == null){
				throw new FileNotFoundException("file is not found");
			}
			//关键2
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
	 * 功能描述:获取字符型属性(可指定默认值)
	 * 
	 * 输入参数
	 * 属性文件对象-props
	 * key
	 * 默认值-defaultValue
	 * 
	 * 返回:字符串
	 * 
	 * 关键点描述:判断属性文件对象中包含key？取该key对应的属性值:取默认值
	 */
	public static String getString(Properties props, String key, String defaultValue){
		String value = defaultValue;
		if(props.containsKey(key)){
			value = props.getProperty(key);
		}
		
		return value;
	}
	/**
	 * 功能描述:获取字符型属性(默认值为空的字符串)
	 */
	public static String getString(Properties props, String key){
		
		return getString(props, key, "");
	}
	/**
	 * 获取数值型属性(默认值为0)
	 */
	public static int getInt(Properties props,String key){
		
		return getInt(props,key,0);
	}	
	/**
	 * 获取数值型属性
	 */
	public static int getInt(Properties props,String key,int defaultValue){
		int value = defaultValue;
		
		//判断属性文件中的key是否包含key
		if(props.containsKey(key)){
			//用转换类型工具将字符串型转换成int,用注释掉的方式当然也可以,但是目的是将所有转换工作做成工具类
			//value = Integer.parseInt(key);
			value = CastUtil.castInt(props.getProperty(key));
		}
		
		return value;
	}

	/**
	 * 获取布尔型属性
	 */
	public static boolean getBoolean(Properties props, String key){
		
		return  getBoolean(props,key,false);
	}
	/**
	 * 获取布尔型属性
	 */
	public static boolean getBoolean(Properties props, String key,Boolean defalutValue){
		
		boolean value = defalutValue;
		if(props.containsKey(key)){
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		
		return value;
	}
}
