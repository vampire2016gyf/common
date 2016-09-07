package indi.vampire.entry;

import indi.vampire.entry.abs.AbstractCacheBean;

/**
 * 标准缓存数据
 * @author vampire
 * @date 2016年2月25日
 *
 */
public class CacheBean extends AbstractCacheBean {
	private static final long serialVersionUID = 1L;
	
	private long time;
	private String hash;
	
	public CacheBean(){
		time = System.currentTimeMillis();
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
