package com.test.MyBatis.type;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlobTypeHandler extends BaseTypeHandler<byte[]>{

	@Override
	public byte[] getResult(ResultSet rs, String columnName) throws SQLException {
		Blob b = rs.getBlob(columnName);
		byte[] result = b.getBytes(1, (int) b.length());
		return result;
	}

	@Override
	public byte[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		Blob b = rs.getBlob(columnIndex);
		byte[] result = b.getBytes(1, (int) b.length());
		return result;
	}

	@Override
	public byte[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Blob b = cs.getBlob(columnIndex);
		byte[] result = b.getBytes(1, (int) b.length());
		return result;
	}

	@Override
	public void setParameterNotNull(PreparedStatement ps, int i, byte[] parameter, JdbcType type) throws SQLException {
		ByteArrayInputStream is = new ByteArrayInputStream(parameter);
		ps.setBinaryStream(i, is, parameter.length);
	}

}
