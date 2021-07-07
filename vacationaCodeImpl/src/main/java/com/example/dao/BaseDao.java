package com.example.dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T> {

	// JDBC驱动和数据库URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hosptial?useUnicode=true&characterEncoding=UTF-8";

	// 数据库用户名和密码
	static final String USER = "root";
	static final String PASS = "200206";

	static Connection conn = null;
	static QueryRunner queryRunner = new QueryRunner();

	static {
		// 1.注册JDBC驱动
		DbUtils.loadDriver(JDBC_DRIVER);

		// 2.打开连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 定义一个变量来接收泛型的类型
	private Class<T> type;

	// 获取T的Class对象，获取泛型的类型，泛型是在被子类继承时才确定
	public BaseDao() {

		// 获取子类的类型
		Class clazz = this.getClass();

		// 获取父类的类型，ParameterizedType表示的是带泛型的类型，
		// getGenericSuperclass()用来获取当前类的父类的类型
		ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();

		// 获取具体的泛型类型 getActualTypeArguments获取具体的泛型的类型，
		// 这个方法会返回一个Type的数组
		Type[] types = parameterizedType.getActualTypeArguments();

		// 获取具体的泛型的类型
		// noinspection unchecked
		this.type = (Class<T>) types[0];
	}

	/**
	 * 手动提交事务 通用的-增删改-操作, 但是一般只用于修改 这里使用的是用户自定义的 Connection 对象,这样的话用户可以自己控制事务
	 * 其他的查询方法不提供 Connection 对象,因为查询不涉及事务的操作.
	 */
	public int updateCommit(Connection conn, String sql, Object... params) {
		int count = 0;
		try {
			count = queryRunner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 数据的增删改 默认一个SQL语句为一个事务,数据库事务交给DBUtils框架进行管理
	 *
	 * @param sql    SQL语句
	 * @param params 执行参数
	 * @return 返回受影响的行数
	 */
	public int update(String sql, Object... params) {
		int count = 0;
		try {
			count = queryRunner.update(conn,sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 自动提交事务的查询方法
	 *
	 * @param sql    SQL 语句
	 * @param params 查询参数
	 * @return 返回泛型
	 */
	public T queryBean(String sql, Object... params) {
		T t = null;
		try {
			t = queryRunner.query(conn, sql,
					new BeanHandler<>(type, new BasicRowProcessor(new GenerousBeanProcessor())), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 自动提交事务的查询所有方法
	 *
	 * @param sql    SQL 语句
	 * @param params 查询参数
	 * @return 返回泛型集合
	 */
	public List<T> queryBeanList(String sql, Object... params) {
		List<T> list = null;
		try {
			list = queryRunner.query(conn, sql,
					new BeanListHandler<>(type, new BasicRowProcessor(new GenerousBeanProcessor())), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 自动提交事务的值查询
	 *
	 * @param sql    SQL 语句
	 * @param params 参数
	 * @return 返回数值如 count(*) sum(total) ...
	 */
	public Object queryValue(String sql, Object... params) {
		Object count = null;
		try {
			count = queryRunner.query(conn,sql, new ScalarHandler<>(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 处理事务提交与回滚
	 *
	 * @param connection 连接的对象
	 */
	public void commit(Connection connection) {
		try {
			DbUtils.commitAndClose(connection);
		} catch (SQLException e) {
			System.out.println("事务提交失败!");
			DbUtils.rollbackAndCloseQuietly(connection);
			e.printStackTrace();
		}
	}
}
