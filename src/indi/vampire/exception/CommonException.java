package indi.vampire.exception;

import org.apache.log4j.Logger;

import indi.vampire.common.DefaultConstants.DefaultErrorCode;
import indi.vampire.common.utils.EmptyUtils;
import indi.vampire.common.DefaultMsgManager;

/**
 * 通用异常信息
 * @author vampire
 * @date 2016年6月15日
 *
 */
public class CommonException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final Logger _log = Logger.getLogger(CommonException.class);
	private static final DefaultMsgManager mm = DefaultMsgManager.getInstance();
	
	private int code;
	private String detail;
	
	/**
	 * 默认的异常
	 * @param detail 异常详情
	 */
	public CommonException(String detail){
		super(mm.getMsg(DefaultErrorCode.FAILURE));
		this.code = DefaultErrorCode.FAILURE;
		this.detail = detail;
	}
	
	/**
	 * 创建通用异常
	 * @param code 异常码
	 * @param detail 异常详情
	 */
	public CommonException(int code, String detail){
		super(mm.getMsg(code));
		this.code = code;
		this.detail = detail;
	}
	
	/**
	 * 根据其他异常创建通用异常
	 * @param detail 异常详情
	 * @param t 下级异常
	 */
	public CommonException(String detail, Throwable t){
		super(mm.getMsg(DefaultErrorCode.FAILURE), t);
		this.code = DefaultErrorCode.FAILURE;
		this.detail = detail;
	}
	
	/**
	 * 根据其他异常创建通用异常
	 * @param code 异常码
	 * @param detail 异常详情
	 * @param t 下级异常
	 */
	public CommonException(int code, String detail, Throwable t){
		super(mm.getMsg(code), t);
		this.code = code;
		this.detail = detail;
	}
	
	/**
	 * 获取异常码
	 * @return
	 */
	public int getCode(){
		return code;
	}
	
	/**
	 * 获取异常详情
	 * @return
	 */
	public String getDetail(){
		return detail;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + "[" + this.code + "]: " + this.getMessage();
	}
	
	@Override
	public void printStackTrace() {
		if(!EmptyUtils.isEmpty(detail)){
			_log.error(detail);
		}
		super.printStackTrace();
	}
}
