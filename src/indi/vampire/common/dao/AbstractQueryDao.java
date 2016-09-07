package indi.vampire.common.dao;

import indi.vampire.common.DefaultConstants.DefaultErrorCode;
import indi.vampire.common.utils.EmptyUtils;
import indi.vampire.common.utils.StringUtils;
import indi.vampire.exception.CommonException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 抽象数据库查询对象
 * @author vampire
 * @date 2016年6月15日
 * @param <T> T为待查询的数据类型
 */
public abstract class AbstractQueryDao<T> extends AbstractDao {
	/**
	 * 根据id获取一个数据对象
	 * @param id 数据对象的id
	 * @return 查询结果（没找到则返回null）
	 * @throws CommonException 生成的错误
	 */
	protected T queryById(String id) throws CommonException {
		return queryById(null, id);
	}
	
	/**
	 * 根据id获取一个数据对象
	 * @param db 数据库
	 * @param id 数据对象的id
	 * @return 查询结果（没找到则返回null）
	 * @throws CommonException 生成的错误
	 */
	protected T queryById(String db, String id) throws CommonException {
		if(EmptyUtils.isEmpty(id)){
			return null;
		}
		List<T> list = queryByDb(db, 0, 0, "id = " + StringUtils.toSqlValue(id));
		if(EmptyUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 根据id获取一个数据对象
	 * @param id 数据对象的id
	 * @return 查询结果（没找到则返回null）
	 * @throws CommonException 生成的错误
	 */
	protected T queryByTid(String id) throws CommonException {
		return queryByTid(null, id);
	}
	
	/**
	 * 根据id获取一个数据对象
	 * @param db 数据库
	 * @param id 数据对象的id
	 * @return 查询结果（没找到则返回null）
	 * @throws CommonException 生成的错误
	 */
	protected T queryByTid(String db, String id) throws CommonException {
		if(EmptyUtils.isEmpty(id)){
			return null;
		}
		List<T> list = queryByDb(db, 0, 0, "t.id = " + StringUtils.toSqlValue(id));
		if(EmptyUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 根据条件查询结果
	 * @param conditions 查询条件组
	 * @return 查询结果列表
	 * @throws CommonException 生成的错误
	 */
	protected List<T> query(Collection<String> conditions) throws CommonException {
		return queryByDb(null, 0, 0, conditions.toArray(new String[0]));
	}
	
	/**
	 * 根据条件查询结果
	 * @param conditions 查询条件组
	 * @return 查询结果列表
	 * @throws CommonException 生成的错误
	 */
	protected List<T> query(String ... conditions) throws CommonException{
		return queryByDb(null, 0, 0, conditions);
	}
	
	/**
	 * 根据条件查询结果
	 * @param start 开始条数
	 * @param limit 查询条数
	 * @param conditions 查询条件组
	 * @return 查询结果列表
	 * @throws CommonException 生成的错误
	 */
	protected List<T> query(int start, int limit, Collection<String> conditions) throws CommonException {
		return queryByDb(null, start, limit, conditions.toArray(new String[0]));
	}
	
	/**
	 * 根据条件查询结果
	 * @param start 开始条数
	 * @param limit 查询条数
	 * @param conditions 查询条件组
	 * @return 查询结果列表
	 * @throws CommonException 生成的错误
	 */
	protected List<T> query(int start, int limit, String ... conditions) throws CommonException {
		return queryByDb(null, start, limit, conditions);
	}
	
	/**
	 * 根据条件查询结果
	 * @param db 数据库
	 * @param conditions 查询条件组
	 * @return 查询结果列表
	 * @throws CommonException 生成的错误
	 */
	protected List<T> queryByDb(String db, Collection<String> conditions) throws CommonException {
		return queryByDb(db, 0, 0, conditions.toArray(new String[0]));
	}
	
	/**
	 * 根据条件查询结果
	 * @param db 数据库
	 * @param conditions 查询条件组
	 * @return 查询结果列表
	 * @throws CommonException 生成的错误
	 */
	protected List<T> queryByDb(String db, String ... conditions) throws CommonException {
		return queryByDb(db, 0, 0, conditions);
	}
	
	/**
	 * 根据条件查询结果
	 * @param db 数据库
	 * @param start 开始条数
	 * @param limit 查询条数
	 * @param conditions 查询条件组
	 * @return 查询结果列表
	 * @throws CommonException 生成的错误
	 */
	protected List<T> queryByDb(String db, int start, int limit, Collection<String> conditions) throws CommonException {
		return queryByDb(null, start, limit, conditions.toArray(new String[0]));
	}
	
	/**
	 * 根据条件查询结果
	 * @param db 数据库
	 * @param start 开始条数
	 * @param limit 查询条数
	 * @param conditions 查询条件组
	 * @return 查询结果列表
	 * @throws CommonException 生成的错误
	 */
	protected List<T> queryByDb(String db, int start, int limit, String ... conditions) throws CommonException {
		List<T> list = new ArrayList<T>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = initQuerySql(start, limit, conditions);
		try{
			_log.debug("query sql -> " + sql);
			
			conn = EmptyUtils.isEmpty(db) ? manager().get() : manager().get(db);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(initDto(rs));
			}
		}catch (SQLException e) {
			throw new CommonException(DefaultErrorCode.SQL_QUERY_ERROR, "error sql -> " + sql, e);
		}finally{
			close(conn, stmt, rs);
		}
		return list;
	}
	
	/**
	 * 生成sql方法，此方法可以重写，用于添加start和limit条件
	 * @param start
	 * @param limit
	 * @param conditions
	 * @return
	 */
	protected String initQuerySql(int start, int limit, String ... conditions) {
		StringBuffer sb = new StringBuffer(initQuerySql(conditions));
		
		if(limit > 0){
			if(start > 0){
				sb.append(" LIMIT " + start + ", " + limit);
			}else{
				sb.append(" LIMIT " + limit);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 生成查询sql
	 * @param conditions 查询条件组
	 * @return 生成的sql
	 */
	abstract protected String initQuerySql(String ... conditions);
	
	/**
	 * 生成数据对象
	 * @param rs 一条数据（不要在此进行滚动操作）
	 * @throws CommonException 生成的错误
	 */
	abstract protected T initDto(ResultSet rs) throws SQLException;
}
