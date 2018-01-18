package com.test.MyBatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterTypeHandler extends BaseTypeHandler<Character> {

	@Override
	public void setParameterNotNull(PreparedStatement ps, int i, Character parameter, JdbcType type) throws SQLException {
		ps.setString(i, parameter.toString());
	}
	
	@Override
	public Character getResult(ResultSet rs, String columnName) throws SQLException {
		String result = rs.getString(columnName);
		if (null != result) {
			return result.charAt(0);
		}
		return null;
	}

	@Override
	public Character getResult(ResultSet rs, int columnIndex) throws SQLException {
		String result = rs.getString(columnIndex);
		if (null != result) {
			return result.charAt(0);
		}
		return null;
	}

	@Override
	public Character getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return null;
	}

}
