package io.github.hastar.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	
	public void fileUpload(MultipartFile[] files, HttpServletRequest req) {
		try {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				
				String path = "D:\\HastarData\\";
				File f = new File(path);
				if (!f.isDirectory()) {
					f.mkdirs();
				}
				
				String originalName = file.getOriginalFilename();
				String ext = originalName.substring(originalName.lastIndexOf(".")+1, originalName.length());
				
				String uuid = UUID.randomUUID().toString();
				OutputStream os = new FileOutputStream(new File(path + uuid));
				byte[] data = file.getBytes();
				os.write(data);
				os.close();
				
				setData();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void setData() {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
	}
	
}
