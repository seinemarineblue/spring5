package com.team404.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginFormInterceptor extends HandlerInterceptorAdapter {
	//로그인 성공 후 post핸들러

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//로그인 성공시 생성되는 세션 user_id
		String user_id = (String)request.getSession().getAttribute("user_id");
		String uri = (String)request.getSession().getAttribute("uri");
		
		//1.로그인 성공,기존에 접근하려는 uri가 있는경우, uri로 이동
		if(uri != null && user_id != null) {
			response.sendRedirect(uri);
		//2.일반적인 로그인 성공인 경우
		} else if(user_id != null) {
			response.sendRedirect( request.getContextPath() );
		}
		//3.로그인 실패인 경우, 기존의 컨트롤러대로 실행됨(해당되는 조 건이 없어,여긴 그냥 슥 지나감)
		
		System.out.println("포스트핸들 실행됨!~");
	}
	
	
}