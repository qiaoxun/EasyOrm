package com.test.EasyOrm;

import com.test.MyBatis.builder.SqlSessionFactoryBuilder;
import com.test.MyBatis.session.SqlSession;
import com.test.MyBatis.session.SqlSessionFactory;

/**
 * Unit test for simple App.
 */
public class AppTest{
	public static void main(String[] args) {
		SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
		SqlSessionFactory ssf = sfb.build("easybatis-config.xml");
		SqlSession session = ssf.openSession(false);
//		session.update("com.test.EasyOrm.IStuMapper.update2");
//		session.commit();
		
		IStuMapper stuMapper = session.getMapper(IStuMapper.class);
		stuMapper.update(new Stu());
		
		session.commit();
	}
}
