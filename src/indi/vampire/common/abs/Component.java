package indi.vampire.common.abs;

import indi.vampire.common.ConfigManager;
import indi.vampire.common.DefaultConstants.DefaultCommon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

/**
 * 模块抽象类，用于管理所有的模块型class
 * @author vampire
 * @date 2016年2月25日
 *
 */
public abstract class Component {
	protected static Logger _log = Logger.getRootLogger();
	protected static final ConfigManager config = ConfigManager.getInstance();
	
	/**
	 * 关闭方法，用于关闭所有包含无参close方法的对象（如果此对象为null，则不会触发close方法）
	 * @param closables 关闭可以关闭的对象组
	 */
	protected static final void close(Object ... closables) {
		for(Object closable : closables){
			if(closable != null){
				try{
					Method close = closable.getClass().getMethod("close");
					close.invoke(closable);
				} catch (NoSuchMethodException | SecurityException e) {
					_log.warn("catch close method error -> " + closable.getClass());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					_log.warn("invoke close method error -> " + closable.getClass());
				} catch (Exception e) {
					_log.warn("close a subject error -> " + closable.getClass());
				}
			}
		}
	}
	
	/**
	 * 对字符串进行url编码（utf8）
	 * @param express 待编码的内容
	 * @return
	 */
	protected static final String encode(String express){
		try{
			return URLEncoder.encode(express, DefaultCommon.DEFAULT_ENCODING);
		}catch(Exception e){
			_log.error("encode error -> " + e);
		}
		return "";
	}
	
	/**
	 * 对字符串进行url解码（utf8）
	 * @param express 待解码的内容
	 * @return
	 */
	protected static final String decode(String express){
		try{
			return URLEncoder.encode(express, DefaultCommon.DEFAULT_ENCODING);
		}catch(Exception e){
			_log.error("decode error -> " + e);
		}
		return "";
	}
}
