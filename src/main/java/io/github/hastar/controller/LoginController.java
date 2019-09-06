package io.github.hastar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String kakaoBack(HttpServletRequest req, HttpServletResponse res) {
		ls.step2(req, res);
		
		return "redirect:/test";
	}
}
