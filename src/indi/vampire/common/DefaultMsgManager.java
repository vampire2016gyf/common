package indi.vampire.common;

import indi.vampire.common.DefaultConstants.DefaultErrorCode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息地图，需要进行一定程度的初始化（调用add方法进行初始化）
 * @author vampire
 * @date 2015年12月17日 下午5:05:15
 * 
 */
public class DefaultMsgManager {
	public static DefaultMsgManager instance;
	public static synchronized DefaultMsgManager getInstance(){
		if(instance == null){
			instance = new DefaultMsgManager();
		}
		return instance;
	}

	private final Map<Integer, String> map = new ConcurrentHashMap<Integer, String>();
	
	protected DefaultMsgManager(){
		if(map.isEmpty()){
			map.put(DefaultErrorCode.SUCCESS, "操作成功");
			map.put(DefaultErrorCode.FAILURE, "操作失败");
			
			map.put(DefaultErrorCode.SQL_QUERY_ERROR, "数据库操作错误");
			
			map.put(DefaultErrorCode.ELASTIC_QUERY_ERROR, "ES操作错误");
			
			map.put(DefaultErrorCode.REQUEST_PARAM_NULL, "必要的请求参数为空");
			map.put(DefaultErrorCode.REQUEST_PARAM_FORMAT_ERROR, "请求参数格式错误");
			
			map.put(DefaultErrorCode.API_CALL_ERROR, "接口调用失败");
			map.put(DefaultErrorCode.API_RESPONSE_RESOLVE_ERROR, "接口返回结果解析失败");
		}
	}
	
	/**
	 * 初始化函数，用于生成新的消息映射
	 * @param code 错误码
	 * @param msg 消息
	 */
	protected void add(int code, String msg){
		map.put(code, msg);
	}
	
	/**
	 * 获取消息函数
	 * @param code 错误码
	 * @return
	 */
	public String getMsg(int code){
		return map.containsKey(code) ? map.get(code) : map.get(DefaultErrorCode.FAILURE);
	}
}
