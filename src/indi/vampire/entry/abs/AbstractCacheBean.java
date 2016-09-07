package indi.vampire.entry.abs;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 属性存储对象，用于保存属性列表
 * @author guyifan
 */
public abstract class AbstractCacheBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> properties;		// 通用属性
	private Map<String, Object> attributes;		// 私有属性
	
	/**
	 * 初始化方法，初始化属性map
	 */
	public AbstractCacheBean(){
		properties = new HashMap<String, Object>();
		attributes = new HashMap<String, Object>();
	}
	
	/**
	 * 获取属性map，深拷贝
	 * @return
	 */
	public Map<String, Object> getProperties() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.putAll(properties);
		return result;
	}
	
	/**
	 * 保存属性map，仅拷贝属性部分，并不是替换属性map指针。注意：会取消null键
	 * @param attributes 属性map
	 */
	public void setProperties(Map<String, Object> attributes) {
		this.properties.clear();
		for(Entry<String, Object> entry: attributes.entrySet()){
			if(entry.getKey() == null){
				continue;
			}
			
			this.properties.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 根据属性的键获取属性，如果属性的键是null则返回null
	 * @param key 属性的键
	 * @return
	 */
	public Object getProperty(String key){
		return key == null ? null : properties.get(key);
	}
	
	/**
	 * 保存一个属性，如果属性的键是null，则不会保存
	 * @param key 属性的键
	 * @param value 属性的值
	 */
	public void setProperty(String key, Object value){
		if(key == null){
			return;
		}
		
		properties.put(key, value);
	}
	
	/**
	 * 获取属性map，深拷贝
	 * @return
	 */
	public Map<String, Object> getAttributes() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.putAll(attributes);
		return result;
	}
	
	/**
	 * 保存属性map，仅拷贝属性部分，并不是替换属性map指针。注意：会取消null键
	 * @param attributes 属性map
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes.clear();
		for(Entry<String, Object> entry: attributes.entrySet()){
			if(entry.getKey() == null){
				continue;
			}
			
			this.attributes.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 根据属性的键获取属性，如果属性的键是null则返回null
	 * @param key 属性的键
	 * @return
	 */
	public Object getAttribute(String key){
		return key == null ? null : attributes.get(key);
	}
	
	/**
	 * 保存一个属性，如果属性的键是null，则不会保存
	 * @param key 属性的键
	 * @param value 属性的值
	 */
	public void setAttribute(String key, Object value){
		if(key == null){
			return;
		}
		
		attributes.put(key, value);
	}
}
