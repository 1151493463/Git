package dao.split;

public class DynamicDataSourceHolder {
	
	private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
	public static final String DB_MASTER = "master";
	public static final String DB_SLAVE = "slave";
	
	public static Object getDbType() {
		// TODO Auto-generated method stub
		String db = contextHolder.get();
		if(db==null) {
			db = DB_MASTER;
		}
		return db;
	}
	/**
	 * 设置线程的DbType
	 * @param str
	 */
	public static void setDbType(String str) {
		contextHolder.set(str);
	}
	/**
	 * 清理连接类型
	 */
	public static void clearDbType() {
		contextHolder.remove();
	}

}
