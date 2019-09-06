package io.github.hastar.harin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HarinController {
	
	@GetMapping("/test")
	public String test() {
		return "first";
	}
	
	@GetMapping("/board")
	public String boardTest() {
		return "board/board";
	}
	
	@GetMapping("/view/{numb}")
	public String viewDetail(@PathVariable String numb) {
		System.out.println("상세보기 No : "+numb);
		return "board/boardDetail";
	}
	
	@GetMapping("/createBoard")
	public String createNewBoard() {
		return "board/boardNew";
	}
}
