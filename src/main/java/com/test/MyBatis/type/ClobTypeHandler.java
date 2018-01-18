package com.test.MyBatis.type;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClobTypeHandler extends BaseTypeHandler<String> {

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		Clob clob = rs.getClob(columnName);
		String value = clob.getSubString(1, (int) clob.length());
		return value;
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		Clob clob = rs.getClob(columnIndex);
		String value = clob.getSubString(1, (int) clob.length());
		return value;
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Clob clob = cs.getClob(columnIndex);
		String value = clob.getSubString(1, (int) clob.length());
		return value;
	}

	@Override
	public void setParameterNotNull(PreparedStatement ps, int i, String parameter, JdbcType type) throws SQLException {
		StringReader reader = new StringReader(parameter);
		ps.setCharacterStream(i, reader, parameter.length());
	}
	
}
