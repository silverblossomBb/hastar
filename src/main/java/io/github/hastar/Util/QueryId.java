package io.github.hastar.Util;

import io.github.hastar.VO.QueryVO;

public class QueryId {
	
	public QueryVO id(String table) {
		QueryVO target = null;
		
		if ("loginInfo".equals(table)) {
			target = new QueryVO("selectLogin", "insertLogin", "deleteLogin", "updateLogin");
		}
		
		return target;
	}
	
}
