package io.github.hastar.harin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HarinController {
	
	@GetMapping("/test")
	public String test() {
		return "first";
	}
}
