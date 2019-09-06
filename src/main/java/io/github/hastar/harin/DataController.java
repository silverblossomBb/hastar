package io.github.hastar.harin;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.hastar.VO.PostVO;

@RestController
public class DataController {
	
	@Autowired
	BoardService bsi;
	@PostMapping("/selectAllData")
	public List<PostVO> selectBoardData(){
		List<PostVO> resultMap =bsi.getAllData(); 
		return resultMap;
	}
	
	@PostMapping("/view/{numb}")
	public PostVO selectOneData(@PathVariable String numb) {
		System.out.println("view : "+numb);
		return bsi.getOneData(Integer.parseInt(numb));
	}
	
	public HashMap<String,Object> crudData(){
		return null;
	}
}
