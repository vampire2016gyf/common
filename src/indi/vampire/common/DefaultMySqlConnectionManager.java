package indi.vampire.common;

import indi.vampire.common.DefaultConstants.DefaultDatabase;
import indi.vampire.common.abs.Component;
import indi.vampire.common.intf.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * 默认mysql数据库连接管理类
 * @author vampire
 * @date 2016年2月25日
 *
 */
public class DefaultMySqlConnectionManager extends Component implements ConnectionManager {
	private static DefaultMySqlConnectionManager instance;
	
	public static synchronized DefaultMySqlConnectionManager getInstance(){
		if(instance == null){
			instance = new DefaultMySqlConnectionManager();
		}
		return instance;
	}
	
	private MysqlDataSource mds;
	
	private DefaultMySqlConnectionManager(){
		_log.info("init default mysql datasource");
		mds = new MysqlDataSource();
		
		mds.setURL(config.get(DefaultDatabase.ADDRESS));
		mds.setUser(config.get(DefaultDatabase.ACCOUNT));
		mds.setPassword(config.get(DefaultDatabase.PASSWORD));
	}

	@Override
	public Connection get() throws SQLException {
		return mds.getConnection();
	}

	@Override
	public Connection get(String id) throws SQLException {
		return mds.getConnection();
	}
}
