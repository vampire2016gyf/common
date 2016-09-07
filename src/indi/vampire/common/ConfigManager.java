package indi.vampire.common;

import indi.vampire.common.utils.EmptyUtils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 配置文件加载类
 * @author vampire
 * @date 2016年2月25日
 *
 */
public final class ConfigManager {
	private static final Logger _log = Logger.getRootLogger();
	private static final String DEFAULT_PATH = "config.properties";
	private static ConfigManager instance;
	
	public static synchronized ConfigManager getInstance(){
		if(instance == null){
			instance = new ConfigManager();
		}
		return instance;
	}
	
	private final Properties config; 
	
	private ConfigManager(){
		config = new Properties();
		reload();
	}
	
	/**
	 * 重新加载默认的配置文件
	 */
	public void reload(){
		config(DEFAULT_PATH);
	}
	
	/**
	 * 重新加载path的配置文件
	 * @param path
	 */
	public void reload(String path){
		config(EmptyUtils.isEmpty(path) ? DEFAULT_PATH : path);
	}
	
	/**
	 * 读取配置文件
	 * @param key 配置信息键
	 * @return 配置信息值
	 */
	public String get(String key){
		return config.getProperty(key);
	}
	
	/**
	 * 读取配置文件
	 * @param path 配置文件classpath路径下相对地址，默认为类路径下的config.properties
	 * @return
	 */
	private void config(String path){
		try {
			config.clear();
			config.load(ConfigManager.class.getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			_log.error("load config file [" + path + "] error -> " + e);
		}
	}
}
