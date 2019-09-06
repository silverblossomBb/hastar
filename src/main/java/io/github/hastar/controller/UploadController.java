package io.github.hastar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.github.hastar.Service.UploadService;
import io.github.hastar.VO.PostVO;
import io.github.hastar.harin.BoardService;
import io.github.hastar.mapper.BoardMapper;

@Controller
public class UploadController {
	
	@Autowired
	UploadService us;
	
	@Autowired
	BoardService bsi;
	
	@RequestMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile[] files, PostVO pv, HttpServletRequest req, HttpSession session) {
		System.out.println("SESSION : "+ session.getAttribute("name") + session.getAttribute("id"));
		int noticeNo = bsi.setNewData(pv,session);
		us.fileUpload(files, req, noticeNo);
	}
	
}
