package indi.vampire.common.utils;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * 校验空的工具类
 * @author vampire
 * @date 2016年2月25日
 *
 */
public final class EmptyUtils {
	/**
	 * 测试字符串是否为空
	 * @param s 需要测试的字符串
	 * @return 如果字符串为null或者空字符串则返回true，其他返回false
	 */
	public static boolean isEmpty(String s){
		return s == null || "".equals(s);
	}
	
	/**
	 * 测试数字是否为空
	 * @param i 需要测试的数据
	 * @return 如果数据是null或者0则返回true，其他返回false
	 */
	public static boolean isEmpty(Integer i){
		return i == null || i == 0;
	}
	
	/**
	 * 测试数字是否为空
	 * @param l 需要测试的数据
	 * @return 如果数据是null或者0则返回true，其他返回false
	 */
	public static boolean isEmpty(Long l){
		return l == null || l == 0;
	}
	
	/**
	 * 测试数字是否为空
	 * @param f 需要测试的数据
	 * @return 如果数据是null或者0则返回true，其他返回false
	 */
	public static boolean isEmpty(Float f){
		return f == null || f == 0;
	}
	
	/**
	 * 测试数字是否为空
	 * @param d 需要测试的数据
	 * @return 如果数据是null或者0则返回true，其他返回false
	 */
	public static boolean isEmpty(Double d){
		return d == null || d == 0;
	}
	
	/**
	 * 测试波尔型是否为空
	 * @param b 需要测试的数据
	 * @return 如果数据是null，则返回true，其他返回false
	 */
	public static boolean isEmpty(Boolean b){
		return b == null;
	}
	
	/**
	 * 测试数组是否为空
	 * @param a 需要测试的数组
	 * @return 如果数据是null或者数组为空则返回true，其他返回false
	 */
	public static boolean isEmpty(Object[] a){
		return a == null || a.length == 0;
	}
	
	/**
	 * 测试集合是否为空
	 * @param c 需要测试的集合
	 * @return 如果数据是null或者空集合则返回true，其他返回false
	 */
	public static boolean isEmpty(Collection<?> c){
		return c == null || c.size() == 0;
	}
	
	/**
	 * 测试Map是否为空
	 * @param map 需要测试的Map
	 * @return 如果数据是null或者空Map则返回true，其他返回false
	 */
	public static boolean isEmpty(Map<?, ?> map){
		return map == null || map.isEmpty();
	}
	
	/**
	 * 测试文件是否为空
	 * @param f 需要测试的文件
	 * @return 如果数据是null或者空文件则返回true，其他返回false
	 */
	public static boolean isEmpty(File f){
		return f == null || !f.exists();
	}
}
