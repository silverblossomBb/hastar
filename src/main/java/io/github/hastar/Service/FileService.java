package io.github.hastar.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.github.hastar.Dao.UploadDao;
import io.github.hastar.Util.QueryId;
import io.github.hastar.VO.UploadVO;

@Service
public class FileService {
	
	@Autowired
	UploadDao uploadDao;
	
	public void fileUpload(MultipartFile[] files, HttpServletRequest req, int noticeNo) {
		try {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				
				String path = "D:\\HastarData\\";
				File f = new File(path);
				if (!f.isDirectory()) {
					f.mkdirs();
				}
				
				String originName = file.getOriginalFilename();
				
				String uuid = UUID.randomUUID().toString();
				OutputStream os = new FileOutputStream(new File(path + uuid));
				byte[] data = file.getBytes();
				os.write(data);
				os.close();
				
				setUpload(new UploadVO(noticeNo, "test", "test", originName, uuid));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void setUpload(UploadVO vo) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", "insert");
		paramMap.put("queryId", query.id("upload"));
		paramMap.put("params", vo);
		uploadDao.db(paramMap);
		
	}
	
	public void fileDownload(int noticeNo, String id) {
		getUpload(noticeNo);
	}
	
	private void getUpload(int noticeNo) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		UploadVO vo = new UploadVO();
		vo.setNoticeNo(noticeNo);
		paramMap.put("queryType", "selectList");
		paramMap.put("queryId", query.id("upload"));
		paramMap.put("params", vo);
		
		List<HashMap<String,Object>> returnList =(List<HashMap<String,Object>>) uploadDao.db(paramMap).get("result");
		returnList.get(0).get("uuid");
		
		String path = "D:\\HastarData\\";
	}
	
	private void setDownload() {
		
	}
}
