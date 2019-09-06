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

@Service
public class LoginService {
	
	@Autowired
	LoginDao ld;
	
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
	
	public void step2(HttpServletRequest req, HttpServletResponse res) {
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
			
			setData(resultMap);
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	public boolean setData(HashMap<String, Object> userMap) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", "insert");
		paramMap.put("queryId", query.id("loginInfo")); // queryVo
		//paramMap.put("params", ) // loginVo
		
		return false;
	}
	
}