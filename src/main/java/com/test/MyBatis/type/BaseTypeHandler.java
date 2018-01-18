package com.test.MyBatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseTypeHandler<T> implements TypeHandler<T> {
	
	@Override
	public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType type) throws SQLException {
		if (null == parameter) {
			ps.setNull(i, type.TYPE_CODE);
		} else {
			setParameterNotNull(ps, i, parameter, type);
		}
	}
	
	public abstract void setParameterNotNull(PreparedStatement ps, int i, T parameter, JdbcType type) throws SQLException;
	
}
