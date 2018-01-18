package com.test.MyBatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FloatTypeHandler extends BaseTypeHandler<Float> {

	@Override
	public Float getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getFloat(columnName);
	}

	@Override
	public Float getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getFloat(columnIndex);
	}

	@Override
	public Float getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getFloat(columnIndex);
	}

	@Override
	public void setParameterNotNull(PreparedStatement ps, int i, Float parameter, JdbcType type) throws SQLException {
		ps.setFloat(i, parameter);
	}

}
