package io.github.hastar.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.github.hastar.Service.UploadService;

@Controller
public class UploadController {
	
	@Autowired
	UploadService us;
	
	@RequestMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest req) {
		us.fileUpload(files, req);
	}
	
}
