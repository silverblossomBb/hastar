package io.github.hastar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BController {
	
	@RequestMapping("/myInfo")
	public String myinfo() {
		return "board/myInfo";
	}
}
