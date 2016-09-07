package indi.vampire.common.utils;

import indi.vampire.common.DefaultConstants.DefaultCommon;
import indi.vampire.common.DefaultConstants.DefaultErrorCode;
import indi.vampire.common.DefaultConstants.ReqMethod;
import indi.vampire.common.abs.Component;
import indi.vampire.entry.ApiResponse;
import indi.vampire.exception.CommonException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;

/**
 * 标准接口调用工具
 * @author vampire
 * @date 2016年2月25日
 *
 */
public final class ApiUtils extends Component {
	private static String ENCODING = DefaultCommon.DEFAULT_ENCODING;
    private static int CONNECT_TIMEOUT = 10000;
    private static int READ_TIMEOUT = 60000;
    
    /**
     * 设置编码格式
     * @param encode 编码格式
     */
    public static final void setEncode(String encode){
    	ENCODING = encode;
    }
    
    /**
     * 设置连接过期时间
     * @param timeout 过期时间（单位：毫秒）
     */
    public static final void setConnectTimeout(int timeout){
    	CONNECT_TIMEOUT = timeout;
    }
    
    /**
     * 设置读取数据过期时间
     * @param timeout 过期时间（单位：毫秒）
     */
    public static final void setReadTimeout(int timeout){
    	READ_TIMEOUT = timeout;
    }
	
    /**
     * 发送http请求，获取接口响应数据
     * @param url 接口地址
     * @param method 请求方法，参数为ReqMethod常量
     * @param params 请求参数，value不能为null
     * @return 标准接口响应
     */
	public static final ApiResponse send(String url, ReqMethod method, Map<String, Object> params) throws CommonException {
		HttpURLConnection conn = null;
		BufferedReader br = null;
		try {
			URL u = new URL(url);
			
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod(method.toString());
			conn.setRequestProperty("Accept-Charset", ENCODING);
			conn.setRequestProperty("contentType", "text/plain; charset=" + ENCODING);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setReadTimeout(READ_TIMEOUT);
			
			params = EmptyUtils.isEmpty(params) ? new HashMap<String, Object>() : params;
			StringBuffer p = new StringBuffer();
			for (String key : params.keySet()) {
				Object value = params.get(key);
				p.append("&").append(key).append("=");
				p.append(value == null ? "" : URLEncoder.encode(String.valueOf(value), ENCODING));
			}
			
			if(p.length() > 0){
				p = p.deleteCharAt(0);
				byte[] b = p.toString().getBytes(ENCODING);
				conn.setDoOutput(true);
				conn.getOutputStream().write(b, 0, b.length);
				conn.getOutputStream().flush();
				conn.getOutputStream().close();
			}

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENCODING));
			String line = null;
			StringBuffer buffer = new StringBuffer();
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			
			_log.debug("receive response from [" + url + "] -> " + buffer.toString());
			
			return new ApiResponse(buffer.toString());
		} catch (IOException e) {
			throw new CommonException(DefaultErrorCode.API_CALL_ERROR, "error url -> " + url, e);
		} catch (JSONException e) {
			throw new CommonException(DefaultErrorCode.API_RESPONSE_RESOLVE_ERROR, "error url -> " + url, e);
		} finally {
			close(br);
			
			if(conn != null){
				conn.disconnect();
			}
		}
	}
}
