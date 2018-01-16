package com.test.EasyOrm;

import java.util.List;
import java.util.Map;

//import org.apache.ibatis.annotations.Param;

public interface IStuMapper {
	void add(Stu stu);
	
	void batchAdd(List<Stu> stuList);
	
	void update(Stu stu);
	
	void update2();
	
	void batchUpdate(List<Stu> stuLIst);
	
	Stu getStuById(/*@Param(value = "id")*/ int id);
	
	List<Stu> listAllStu();
	
	void deleteStuById(int id);
	
	void batchDelete(String ids);
	
	Map<String, String> getMapValue(int id);
}
