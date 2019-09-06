package io.github.hastar.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DBTestController {
	
	@Autowired
	SqlSession sql;
	
	@RequestMapping("/dbtest")
	public String dbtest() {
			
			int cnt = (Integer) sql.selectOne("sql.test");
			System.out.println(cnt);
		
		return "dbtest";
	}
}
