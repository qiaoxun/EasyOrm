package com.test.MyBatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class DateTypeHandler extends BaseTypeHandler<Date> {

	@Override
	public Date getResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp ts = rs.getTimestamp(columnName);
		Date d = new Date(ts.getTime());
		return d;
	}

	@Override
	public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp ts = rs.getTimestamp(columnIndex);
		Date d = new Date(ts.getTime());
		return d;
	}

	@Override
	public Date getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp ts = cs.getTimestamp(columnIndex);
		Date d = new Date(ts.getTime());
		return d;
	}

	@Override
	public void setParameterNotNull(PreparedStatement ps, int i, Date parameter, JdbcType type) throws SQLException {
		ps.setTimestamp(i, new Timestamp(parameter.getTime()));
	}

}
