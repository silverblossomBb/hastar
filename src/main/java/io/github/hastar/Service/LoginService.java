package io.github.hastar.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.hastar.Dao.LoginDao;
import io.github.hastar.Util.HttpUtil;
import io.github.hastar.Util.QueryId;
import io.github.hastar.VO.LoginVO;
import net.sf.json.JSONObject;

@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	public void step1(HttpServletResponse res) {		
		try {
			String url = "https://kauth.kakao.com/oauth/authorize";
			url += "?client_id=9bf33cae98c2bcbb6198806e4a3f9f25&redirect_uri=";
			url += URLEncoder.encode("http://gdj16.gudi.kr:20016/KakaoBack", "UTF-8");
			url += "&response_type=code";
			res.sendRedirect(url);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	};
	
	public HashMap<String, Object> step2(HttpServletRequest req, HttpServletResponse res) {
		HashMap<String, Object> status = null;
		
		try {
			String code = req.getParameter("code");		
			String url = "https://kauth.kakao.com/oauth/token";
			url += "?client_id=9bf33cae98c2bcbb6198806e4a3f9f25&redirect_uri=";
			url += URLEncoder.encode("http://gdj16.gudi.kr:20016/KakaoBack", "UTF-8");
			url += "&code=" + code;
			url += "&grant_type=authorization_code";
			HashMap<String, Object> resultMap = HttpUtil.getUrl(url);
				
			String userUrl = "https://kapi.kakao.com/v2/user/me";
			userUrl += "?access_token=" + resultMap.get("access_token");
			resultMap = HttpUtil.getUrl(userUrl);
			System.out.println(resultMap);
			
			status = setData(resultMap);
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return status;
	}
	
	private HashMap<String, Object> setData(HashMap<String, Object> userMap) {
		JSONObject p = JSONObject.fromObject(userMap.get("properties"));
		String id = userMap.get("id").toString();
		String name = p.getString("nickname");
		String image = p.getString("profile_image");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", "insert");
		paramMap.put("queryId", query.id("loginInfo")); // queryVo
		paramMap.put("params", new LoginVO(id, name, image)); // loginVo
		
		return loginDao.db(paramMap);
	}
	
}
