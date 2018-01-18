package com.test.MyBatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringTypeHandler extends BaseTypeHandler<String> {

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getString(columnName);
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getString(columnIndex);
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getString(columnIndex);
	}

	@Override
	public void setParameterNotNull(PreparedStatement ps, int i, String parameter, JdbcType type) throws SQLException {
		ps.setString(i, parameter);
	}

}
