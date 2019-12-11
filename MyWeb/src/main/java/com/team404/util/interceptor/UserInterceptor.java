package com.team404.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter {

	//세션에 user_id가 없는 경우만 실행
	public void saveURI(HttpServletRequest request) {
		String uri = request.getRequestURI(); //요청정보중에 URI정보를 받음
		String query = request.getQueryString();//요청정보중에 매개값을 받음
		//System.out.println(uri);
		//System.out.println(query);
		HttpSession session = request.getSession();//세션에정보
		session.setAttribute("uri", uri+(query == null ? "": "?"+query) );//세션에 uri요청과 데이터값을 세션에 저장
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("user_id");
		
		if(id == null) {
			saveURI(request); //주소에 대한 정보를 얻음
			response.sendRedirect( request.getContextPath() + "/user/userLogin"); //절대경로
			return false;//컨트롤러 실행을 막음
		} else {
			return true; //컨트롤러 실행
		}
	}
	
}