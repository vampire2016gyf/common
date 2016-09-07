package indi.vampire.common.intf;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接管理类
 * @author vampire
 * @date 2016年2月25日
 *
 */
public interface ConnectionManager {
	/**
	 * 获取一个默认的连接点
	 * @return
	 */
	public Connection get() throws SQLException;
	
	/**
	 * 根据id获取一个连接点
	 * @return
	 */
	public Connection get(String id) throws SQLException;
}
