package io.github.hastar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BController {
	
	@RequestMapping("/myInfo")
	public String myinfo(HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/test";
		}
		return "board/myInfo";
	}
}
