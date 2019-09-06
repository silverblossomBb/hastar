package io.github.hastar.Dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.hastar.VO.LoginVO;
import io.github.hastar.VO.QueryVO;

@Repository
public class LoginDao {
	
	@Autowired
	SqlSession sql;
	
	public List<Object> db(HashMap<String, Object> paramMap) {
		List<Object> result = null;
		
		// queryType // queryId // params
		String queryType = paramMap.get("queryType").toString();
		QueryVO queryId = (QueryVO) paramMap.get("queryId");
		LoginVO params = (LoginVO) paramMap.get("params");
		
		switch (queryType) {
		case "insert":
			sql.insert(queryId.getInsert(), params);
			break;
		}
		
		return result;
	}
}
