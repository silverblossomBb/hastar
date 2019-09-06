package io.github.hastar.harin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.hastar.VO.PostVO;
import io.github.hastar.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	BoardMapper bm;
	
	public List<PostVO> getAllData(){
		return bm.getAllData();
	}
}
