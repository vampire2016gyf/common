package indi.vampire.entry.abs;

import indi.vampire.common.DefaultConstants.DefaultCommon;

import java.util.Date;

/**
 * 用于添加实例对象创建时间属性，方便其在转化JSON时可以格式化显示
 * @author vampire
 * @date 2016年5月24日
 *
 */
public class Timable {
	private Date ctime;			// 实际创建时间
	private String showCtime;	// 用于显示的创建时间
	private Long timestamp;		// 时间戳(13位)
	
	/**
	 * 获取实际创建时间对象
	 * @return
	 */
	public Date fetchCtime(){
		return ctime;
	}
	
	public String getCtime(){
		return showCtime;
	}
	
	public void setCtime(Date ctime){
		this.ctime = ctime;
		this.showCtime = DefaultCommon.DATETIME_FORMATTER.format(ctime);
		this.timestamp = ctime.getTime();
	}
	
	public Long getCtimestamp(){
		return timestamp;
	}
}
