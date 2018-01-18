package com.test.MyBatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerTypeHandler extends BaseTypeHandler<Integer> {

	@Override
	public Integer getResult(ResultSet rs, String columnName) throws SQLException {
		Integer result = rs.getInt(columnName);
		return result;
	}

	@Override
	public Integer getResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer result = rs.getInt(columnIndex);
		return result;
	}

	@Override
	public Integer getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer result = cs.getInt(columnIndex);
		return result;
	}

	@Override
	public void setParameterNotNull(PreparedStatement ps, int i, Integer parameter, JdbcType type) throws SQLException {
		ps.setInt(i, parameter);
	}

}
