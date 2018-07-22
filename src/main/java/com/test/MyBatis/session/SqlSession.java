package com.test.MyBatis.session;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * The primary java interface for working with mybatis
 * Through this interface you can execute commands, get mappers and manage transactions
 * 
 * @author Joey
 *
 */
public interface SqlSession extends Closeable {
	
	/**
	 * Retrieve a single row mapped from the statement key
	 * @param statement
	 * @return
	 */
	<T> T selectOne(String statement);
	
	/**
	 * Retrieve a single row mapped from the statement key and parameter
	 * @param statement
	 * @return
	 */
	<T> T selectOne(String statement, Object parameter);
	
	/**
	 * Retrieve a list of mapped objects from the statement key 
	 * @param statement
	 * @return
	 */
	<E> List<E> selectList(String statement);
	
	/**
	 * Retrieve a list of mapped objects from the statement key and parameter 
	 * @param statement
	 * @return
	 */
	<E> List<E> selectList(String statement, Object parameter);
	
	/**
	 * This selectMap a special case in that it is designed to convert a 
	 * list of results into a map based on one of the properties 
	 * in the resulting objects
	 * EG. selectMap("getMapValue", "id"); return a map of 
	 * {1={birthDay=19770725111111, id=1, age=35}, 2={birthDay=19770725111111, id=2, age=35}}
	 * @return
	 */
	<K, V> Map<K, V> selectMap(String statement, String mapKey);
	
	/**
	 * This selectMap a special case in that it is designed to convert a 
	 * list of results into a map based on one of the properties 
	 * in the resulting objects
	 * @return
	 */
	<K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey);
	
	/**
	 * Execute an insert statement
	 * @param statement
	 * @return
	 */
	int insert(String statement);
	
	/**
	 * Execute an insert statement with a given parameter object. 
	 * Any generated autoincrement values or selectKey entities will
	 * modify the given parameter object properties. Only number of rows affected will be returned.
	 * @param statement
	 * @param parameter
	 * @return
	 */
	int insert(String statement, Object parameter);
	
	/**
	 * Execute a update statement, Only number of rows affected will be returned.
	 * @param statement
	 * @return
	 */
	int update(String statement);
	
	/**
	 * Execute a update statement with a given parameter object, Only number of rows affected will be returned.
	 * @param statement
	 * @param parameter
	 * @return
	 */
	int update(String statement, Object parameter);
	
	/**
	 * Execute a delete statement, Only number of rows affected will be returned.
	 * @param statement
	 * @return
	 */
	int delete(String statement);
	
	/**
	 * Execute a delete statement with a given parameter object, Only number of rows affected will be returned.
	 * @param statement
	 * @param parameter
	 * @return
	 */
	int delete(String statement, Object parameter);
	
	/**
	 * Flushes batch statements and commits datasource connection.
	 * Note that datasource connection will not be commited, if there is no updates/inserts/deletes were called.
	 */
	void commit();
	
	/**
	 * Flushes batch statements and commits datasource connection.
	 * @param force
	 */
	void commit(boolean force);
	
	/**
	 * Discards pending batch statements and rolls datasource connection back.
	 * Note that datasource connection will not be rolled back, if no update/insert/delete were called
	 */
	void rollback();
	
	/**
	 * Discards pending batch statements and rolls datasource connection back.
	 * Note that datasource connection will not be rolled back, if no update/insert/delete were called
	 */
	void rollback(boolean force);
	
	/**
	 * close the session
	 */
	@Override
	void close() throws IOException;
	
	/**
	 * Clears local session cache
	 */
	void clearCache();
	
	/**
	 * Retrieves current configuration
	 * @return
	 */
	Configuration getConguration();
	
	/**
	 * Retrieves inner datasource connection
	 * @return
	 */
	Connection getConnection();
	
	/**
	 * Retrieves a mapper
	 * @param clazz
	 * @return
	 */
	<T> T getMapper(Class<T> clazz);
}
