package indi.vampire.entry;

import indi.vampire.common.utils.EmptyUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 标准接口响应，重写toString方法，方便调试
 * @author vampire
 * @date 2016年2月25日
 *
 */
public class ApiResponse {
	private boolean success;
	private int code;
	private String msg;
	private String result;
	private String response;
	
	/**
	 * 构建api响应
	 * @param response 响应结果
	 * @throws Exception 解析失败
	 */
	public ApiResponse(String response) throws JSONException {
		this.response = response;
		
		JSONObject json = JSONObject.fromObject(response);
		result = json.getString("result");
		msg = json.getString("msg");
		code = json.getInt("code");
		success = json.getBoolean("success");
	}

	public boolean success() {
		return success;
	}

	public int code() {
		return code;
	}

	public String msg() {
		return msg;
	}

	/**
	 * 获取JSONObject格式返回值
	 * @return
	 */
	public JSONObject getResultByJson() {
		if(EmptyUtils.isEmpty(result)){
			return null;
		}else{
			try{
				return JSONObject.fromObject(result);
			}catch(Exception e){
				return null;
			}
		}
	}
	
	/**
	 * 获取JSONArray格式返回值
	 * @return
	 */
	public JSONArray getResultByArray() {
		if(EmptyUtils.isEmpty(result)){
			return null;
		}else{
			try{
				return JSONArray.fromObject(result);
			}catch(Exception e){
				return null;
			}
		}
	}
	
	/**
	 * 获取String格式返回值
	 * @return
	 */
	public String getResultByString() {
		return result;
	}
	
	/**
	 * 获取响应原文
	 * @return
	 */
	public String getResponseString(){
		return response;
	}
	
	@Override
	public String toString() {
		return "code -> " + code + ", success -> " + success + ", msg -> " + msg + ", result -> " + result;
	}
}
