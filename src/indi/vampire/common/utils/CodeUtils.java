package indi.vampire.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 随机码生成工具
 * @author vampire
 * @date 2016年6月15日
 *
 */
public final class CodeUtils {
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static final String[] CHARS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
		 "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", 
		 "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", 
		 "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	
	/**
	 * 获取随机数字字符串
	 * @param length 字符串长度
	 * @return
	 */
	public static final String getRandomNumber(int length){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++){
			sb.append(RANDOM.nextInt(10));
		}
		return sb.toString();
	}
	
	/**
	 * 获取随机数字大写字母字符串
	 * @param length 字符串长度
	 * @return
	 */
	public static final String getRandomChar(int length){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++){
			sb.append(CHARS[RANDOM.nextInt(36)]);
		}
		return sb.toString();
	}
	
	/**
	 * 获取随机数字大小写字母字符串
	 * @param length 字符串长度
	 * @return
	 */
	public static final String getRandomCaseSensitiveChar(int length){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++){
			sb.append(CHARS[RANDOM.nextInt(62)]);
		}
		return sb.toString();
	}
	
	/**
	 * 生成32位小写字符串id
	 * @return
	 */
	public static final String initId(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
