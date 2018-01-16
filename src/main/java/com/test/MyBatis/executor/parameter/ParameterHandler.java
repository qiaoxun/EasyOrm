package com.test.MyBatis.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterHandler {
	
	Object getParameterObject();
	
	void setParamenters(PreparedStatement statement) throws SQLException;
	
}
