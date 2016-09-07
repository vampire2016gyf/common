package indi.vampire.common.utils;

import indi.vampire.common.abs.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 * @author vampire
 * @date 2016年6月15日
 *
 */
public final class EncodeUtils extends Component {
	/**
	 * MD5加密
	 * @param express 明文
	 * @return 密文
	 */
	public static String MD5(String express){
		if(express == null){
			return null;
		}
		return encode(express, "MD5");
	}
	
	/**
	 * SHA1加密
	 * @param express 明文
	 * @return 密文
	 */
	public static String SHA1(String express){
		if(express == null){
			return null;
		}
		return encode(express, "SHA-1");
	}
	
	/**
	 * 加密方法
	 * @param express 明文
	 * @param type 加密类型
	 * @return 密文
	 */
	private static String encode(String express, String type){
		StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance(type);
			byte[] cipher = md.digest(express.getBytes());
			for (byte b : cipher) {
				buffer.append(Integer.toHexString(b >>> 4 & 0xf));
				buffer.append(Integer.toHexString(b & 0xf));
			}
		} catch (NoSuchAlgorithmException e) {
			_log.error("no [" + type + "] encode algorithm -> " + e);
		}
		
		return buffer.toString();
	}
}
