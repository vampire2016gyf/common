package indi.vampire.common.utils;

import indi.vampire.common.DefaultConstants.DefaultCommon;

import java.util.Collection;
import java.util.Date;

/**
 * 字符串处理工具类
 * @author vampire
 * @date 2016年2月25日
 *
 */
public final class StringUtils {
	/**
	 * 拼接函数，把一个列表的所有内容拼接成一个sql串，并用“,”分开
	 * 如果数据类型为String，则会调用toSqlString方法
	 * 如果为空，则会拼接“NULL”，如果是数字，则会直接拼接
	 * 如果为Boolean类型，则会拼接“0”作为false，“1”作为true
	 * @param collection 需要拼接的对象列表
	 * @return
	 */
	public static String toSqlValues(Collection<?> collection){
		if(EmptyUtils.isEmpty(collection)){
			return "(NULL)";
		}
		StringBuffer sb = new StringBuffer();
		for(Object o : collection){
			sb.append(",").append(toSqlValue(o));
		}
		return "(" + sb.substring(1) + ")";
	}
	
	/**
	 * 拼接函数，把一个列表的所有内容拼接成一个sql串，并用“,”分开
	 * 如果数据类型为String，则会调用toSqlString方法
	 * 如果为空，则会拼接“NULL”，如果是数字，则会直接拼接
	 * 如果为Boolean类型，则会拼接“0”作为false，“1”作为true
	 * @param array 需要拼接的对象
	 * @return
	 */
	public static String toSqlValues(Object ... array){
		if(EmptyUtils.isEmpty(array)){
			return "(NULL)";
		}
		StringBuffer sb = new StringBuffer();
		for(Object o : array){
			sb.append(",").append(toSqlValue(o));
		}
		return "(" + sb.substring(1) + ")";
	}
	
	/**
	 * 将字符串转化为sql下的字符串，并将其中的单引号转义，防止sql注入。如果字符串为null，则返回字符串“NULL”
	 * @param obj 需要转化的字符串
	 * @return
	 */
	public static String toSqlValue(Object obj){
		String result;
		if(obj == null){
			result = "NULL";
		}else if(obj instanceof Number){
			result = obj.toString();
		}else if (obj instanceof Boolean){
			result = (Boolean) obj ? "1" : "0";
		}else if (obj instanceof Date){
			result = "'" + DefaultCommon.DATETIME_FORMATTER.format((Date) obj) + "'";
		}else{
			result = "'" + obj.toString().replace("'", "''") + "'";
		}
		return result;
	}
	
	/**
	 * 将字符串转化为sql下的字段名，并在两边加上“`”，防止sql注入，解析失败返回null。如果有“.”做分隔符，则分隔的每个字符串都会去掉两端的空格并添加“`”。
	 * @param str 需要转化的字符串
	 * @return
	 */
	public static String toSqlCode(String str){
		str = str == null ? null : str.replace("`", "").trim();
		if(EmptyUtils.isEmpty(str)){
			return null;
		}
		String result = "";
		for(String value : str.split("[.]")){
			result += "." +  "`" + value.trim() + "`";
		}
		return result.substring(1);
	}
	
	/**
	 * 将字符串转去除两端空格，如果为null，返回空字符串
	 * @param str 待转化的字符串
	 * @return
	 */
	public static String parseString(String str){
		if(str == null){
			return "";
		}
		return str.trim();
	}
	
	/**
	 * 将字符串转化为波尔型
	 * @param str 要转化的字符串
	 * @return 如果字符串同“true”（不区分大小写），则返回true，其他情况返回false
	 */
	public static boolean parseBoolean(String str){
		return Boolean.parseBoolean(str);
	}
	
	/**
	 * 将字符串转化为整数
	 * @param str 要转化的字符串
	 * @return 如果能转化则返回转化的整数，不能则返回0
	 */
	public static int parseInteger(String str){
		int i = 0;
		try{
			i = Integer.parseInt(str);
		}catch (Exception e) { }
		return i;
	}
	
	/**
	 * 将字符串转化为长整数
	 * @param str 要转化的字符串
	 * @return 如果能转化则返回转化的长整数，不能则返回0
	 */
	public static long parseLong(String str){
		long l = 0l;
		try{
			l = Long.parseLong(str);
		}catch (Exception e) { }
		return l;
	}
	
	/**
	 * 将字符串转化为单精度数字
	 * @param str 要转化的字符串
	 * @return 如果能转化则返回转化的单精度数字，不能则返回0
	 */
	public static float parseFloat(String str) {
		float f = 0f;
		try{
			f = Float.parseFloat(str);
		}catch (Exception e) { }
		return f;
	}
	
	/**
	 * 将字符串转化为双精度数字
	 * @param str 要转化的字符串
	 * @return 如果能转化则返回转化的双精度数字，不能则返回0
	 */
	public static double parseDouble(String str) {
		double d = 0d;
		try{
			d = Double.parseDouble(str);
		}catch (Exception e) { }
		return d;
	}
}
