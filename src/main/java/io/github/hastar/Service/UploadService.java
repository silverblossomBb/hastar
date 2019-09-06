package io.github.hastar.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.github.hastar.Dao.UploadDao;
import io.github.hastar.Util.QueryId;
import io.github.hastar.VO.UploadVO;

@Service
public class UploadService {
	
	@Autowired
	UploadDao uploadDao;
	
	public void fileUpload(MultipartFile[] files, HttpServletRequest req) {
		try {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				
				String path = "D:\\HastarData\\";
				File f = new File(path);
				if (!f.isDirectory()) {
					f.mkdirs();
				}
				
				String originName = file.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf(".")+1, originName.length());
				
				String uuid = UUID.randomUUID().toString();
				OutputStream os = new FileOutputStream(new File(path + uuid));
				byte[] data = file.getBytes();
				os.write(data);
				os.close();
				
				setData(new UploadVO(100, "test", "test", originName, ext, uuid));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void setData(UploadVO vo) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", "insert");
		paramMap.put("queryId", query.id("upload"));
		paramMap.put("params", vo);
		uploadDao.db(paramMap);
		
	}
	
}
