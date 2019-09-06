package io.github.hastar.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.hastar.Service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService ls;
	
	@RequestMapping("/login")
	public void login(HttpServletResponse res) {
		ls.step1(res);
	}
	
	@RequestMapping("/KakaoBack")
	public String kakaoBack(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		HashMap<String, Object> resultMap = ls.step2(req, res); 
		if ("true".equals(resultMap.get("status"))) {
			System.out.println("ddd");
			session.setAttribute("id", resultMap.get("id"));
			session.setAttribute("name", resultMap.get("name"));
			session.setAttribute("image", resultMap.get("image"));
		}
		
		return "redirect:/test";
	}
}
