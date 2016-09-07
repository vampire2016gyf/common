package indi.vampire.common.dao;

import indi.vampire.common.DefaultConstants.DefaultErrorCode;
import indi.vampire.common.DefaultMySqlConnectionManager;
import indi.vampire.common.abs.Component;
import indi.vampire.common.intf.ConnectionManager;
import indi.vampire.common.utils.EmptyUtils;
import indi.vampire.exception.CommonException;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

/**
 * 抽象数据库访问对象
 * @author vampire
 * @date 2016年6月15日
 *
 */
public abstract class AbstractDao extends Component{
	/**
	 * 默认的数据库连接管理器
	 * 用于生成数据库连接
	 */
	private ConnectionManager manager = DefaultMySqlConnectionManager.getInstance();
	
	/**
	 * 设置默认的数据库连接管理器
	 * @param manager 数据库连接管理器
	 */
	protected void setManager(ConnectionManager manager){
		this.manager = manager;
	}
	
	/**
	 * 获取数据库连接管理器
	 * @return
	 */
	protected ConnectionManager manager(){
		return manager;
	}
	
	/**
	 * 执行一句带参数的DML语句
	 * @param sql 待执行的DML语句
	 * @param params 传入的参数
	 * @return 执行的行数
	 * @throws CommonException 生成的错误
	 */
	protected final int update(String sql, List<?> params) throws CommonException {
		return updateByDb(null, sql, params.toArray());
	}
	
	/**
	 * 执行一句带参数的DML语句
	 * @param sql 待执行的DML语句
	 * @param params 传入的参数
	 * @return 执行的行数
	 * @throws CommonException 生成的错误
	 */
	protected final int update(String sql, Object ... params) throws CommonException {
		return updateByDb(null, sql, params);
	}
	
	/**
	 * 执行一句带参数的DML语句
	 * @param db 执行语句的数据库（空则使用默认数据库）
	 * @param sql 待执行的DML语句
	 * @param params 传入的参数
	 * @return 执行的行数
	 * @throws CommonException 生成的错误
	 */
	protected final int updateByDb(String db, String sql, List<?> params) throws CommonException {
		return updateByDb(db, sql, params.toArray());
	}

	/**
	 * 执行一句带参数的DML语句
	 * @param db 执行语句的数据库（空则使用默认数据库）
	 * @param sql 待执行的DML语句
	 * @param params 传入的参数
	 * @return 执行的行数
	 * @throws CommonException 生成的错误
	 */
	protected final int updateByDb(String db, String sql, Object ... params) throws CommonException {
		_log.debug("execute sql -> " + sql);
		
		Connection conn = null;
		CallableStatement stmt = null;
		try{
			conn = EmptyUtils.isEmpty(db) ? manager().get() : manager().get(db);
			stmt = conn.prepareCall(sql);
			for(int i = 0; i < params.length; i++){
				if(params[i] == null){
					stmt.setNull(i + 1, Types.NULL);
				}else{
					stmt.setObject(i + 1, params[i]);
				}
			}
			return stmt.executeUpdate();
		}catch(Exception e){
			throw new CommonException(DefaultErrorCode.SQL_QUERY_ERROR, "error sql -> " + sql, e);
		}finally{
			close(conn, stmt);
		}
	}
	
	/**
	 * 执行插入一条数据
	 * @param sql 待执行的DML语句
	 * @param obj 数据对象
	 * @param fields 待插入的属性名称（按照sql顺序放置）
	 * @return 执行的行数
	 * @throws CommonException 生成的错误
	 */
	protected final int insert(String sql, Object obj, String ... fields) throws CommonException {
		return insertByDb(null, sql, obj, fields);
	}
	
	/**
	 * 执行插入一条数据
	 * @param db 执行语句的数据库（空则使用默认数据库）
	 * @param sql 待执行的DML语句
	 * @param obj 数据对象
	 * @param fields 待插入的属性名称（按照sql顺序放置）
	 * @return 执行的行数
	 * @throws CommonException 生成的错误
	 */
	protected int insertByDb(String db, String sql, Object obj, String ... fields) throws CommonException {
		if(obj == null || EmptyUtils.isEmpty(fields)){
			// 未找到待插入的数据
			return 0;
		}
		
		_log.debug("execute sql -> " + sql);
		
		Connection conn = null;
		CallableStatement stmt = null;
		try{
			conn = EmptyUtils.isEmpty(db) ? manager().get() : manager().get(db);
			stmt = conn.prepareCall(sql);
			Class<?> c = obj.getClass();
			for(int i = 0; i < fields.length; i++){
				Field f = c.getDeclaredField(fields[i]);
				f.setAccessible(true);
				Object value = f.get(obj);
				
				if(value == null){
					stmt.setNull(i + 1, Types.NULL);
				}else{
					stmt.setObject(i + 1, value);
				}
			}
			return stmt.executeUpdate();
		}catch(Exception e){
			throw new CommonException(DefaultErrorCode.SQL_QUERY_ERROR, "error sql -> " + sql, e);
		}finally{
			close(conn, stmt);
		}
	}
	
	/**
	 * 插入一组数据
	 * @param sql 待执行的DML语句
	 * @param list 一组数据对象
	 * @param fields 待插入的属性名称（按照sql顺序放置）
	 * @return 执行的行数
	 * @throws CommonException 生成的错误
	 */
	protected final int[] insertBatch(String sql, List<?> list, String ... fields) throws CommonException {
		return insertBatchByDb(null, sql, list, fields);
	}
	
	/**
	 * 执行插入一组数据
	 * @param db 执行语句的数据库（空则使用默认数据库）
	 * @param sql 待执行的DML语句
	 * @param list 一组数据对象
	 * @param fields 待插入的属性名称（按照sql顺序放置）
	 * @return 执行的行数
	 * @throws CommonException 生成的错误
	 */
	protected int[] insertBatchByDb(String db, String sql, List<?> list, String ... fields) throws CommonException {
		if(EmptyUtils.isEmpty(list) || EmptyUtils.isEmpty(fields)){
			// 未找到待插入的数据
			return new int[0];
		}
		
		_log.debug("execute sql -> " + sql);
		
		Connection conn = null;
		CallableStatement stmt = null;
		try{
			conn = EmptyUtils.isEmpty(db) ? manager().get() : manager().get(db);
			stmt = conn.prepareCall(sql);
			for(Object obj : list){
				Class<?> c = obj.getClass();
				for(int i = 0; i < fields.length; i++){
					Field f = c.getDeclaredField(fields[i]);
					f.setAccessible(true);
					Object value = f.get(obj);
					
					if(value == null){
						stmt.setNull(i + 1, Types.NULL);
					}else{
						stmt.setObject(i + 1, value);
					}
				}
				stmt.addBatch();
			}
			return stmt.executeBatch();
		}catch(Exception e){
			throw new CommonException(DefaultErrorCode.SQL_QUERY_ERROR, "error sql -> " + sql, e);
		}finally{
			close(conn, stmt);
		}
	}
}
