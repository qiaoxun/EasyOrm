package com.test.MyBatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoubleTypeHandler extends BaseTypeHandler<Double> {

	@Override
	public Double getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getDouble(columnName);
	}

	@Override
	public Double getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getDouble(columnIndex);
	}

	@Override
	public Double getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getDouble(columnIndex);
	}

	@Override
	public void setParameterNotNull(PreparedStatement ps, int i, Double parameter, JdbcType type) throws SQLException {
		ps.setDouble(i, parameter);
	}

}
