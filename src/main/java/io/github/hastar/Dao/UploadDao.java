package io.github.hastar.Dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.hastar.VO.QueryVO;
import io.github.hastar.VO.UploadVO;

@Repository
public class UploadDao {
	
	@Autowired
	SqlSession sql;
	
	public HashMap<String, Object> db(HashMap<String, Object> paramMap) {
		HashMap<String, Object> resultMap = null;
		
		// queryType // queryId // params
		String queryType = paramMap.get("queryType").toString();
		QueryVO queryId = (QueryVO) paramMap.get("queryId");
		UploadVO params = (UploadVO) paramMap.get("params");
		
		switch (queryType) {
		case "insert":
			sql.insert(queryId.getInsert(), params);
			break;
		case "selectList":
			resultMap = new HashMap<String, Object>();
			resultMap.put("result", sql.selectList(queryId.getSelect(), params));
			break;
		}
		
		return resultMap;
	}
}
