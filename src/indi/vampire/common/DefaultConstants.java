package indi.vampire.common;

import java.text.SimpleDateFormat;

/**
 * 默认常量类
 * @author vampire
 * @date 2016年2月25日
 *
 */
public final class DefaultConstants {
	public static class DefaultCommon {
		public static final SimpleDateFormat DATETIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
		public static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("hh:mm:ss");
		
		public static class Time {
			public static final long SECOND = 1000l;
			public static final long MINUTE = 60 * SECOND;
			public static final long HOUR = 60 * MINUTE;
			public static final long DAY = 24 * HOUR;
			public static final long MONTH = 30 * DAY;
			public static final long YEAR = 365 * DAY;
		}
		
		public static final String DEFAULT_ENCODING = "utf-8";
	}
	
	public static class DefaultDatabase {
		public static final String ADDRESS = "common.database.address";
		public static final String ACCOUNT = "common.database.account";
		public static final String PASSWORD = "common.database.password";
	}
	
	public static class DefaultElastic {
		public static final String IP = "common.elastic.ip";
		public static final String PORT = "common.elastic.port";
	}
	
	public static class DefaultErrorCode {
		public static final int SUCCESS = 1000;
		public static final int FAILURE = 1001;
		
		public static final int SQL_QUERY_ERROR = 1101;
		
		public static final int ELASTIC_QUERY_ERROR = 1111;
		
		public static final int REQUEST_PARAM_NULL = 1201;
		public static final int REQUEST_PARAM_FORMAT_ERROR = 1202;
		
		public static final int API_CALL_ERROR = 1301;
		public static final int API_RESPONSE_RESOLVE_ERROR = 1302;
	}
	
	public static enum ReqMethod {
		GET, POST, HEAD, TRACE, PUT, DELETE, OPTIONS, CONNECT
	}
}
