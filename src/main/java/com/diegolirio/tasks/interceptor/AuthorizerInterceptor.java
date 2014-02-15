package com.diegolirio.tasks.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorizerInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//System.out.println(request.getRequestURI());
		//String next_page = request.getParameter("next") == null || "".equals(request.getParameter("next")) ? "/list" : request.getParameter("next");
		//System.out.println("NEXT "+next_page);		
		String uri = request.getRequestURI();
		if(uri.endsWith("tasks/") || uri.endsWith("tasks") || uri.contains("/static/")) {
			return true;
		}
		
		if(request.getSession().getAttribute("user") != null) {
			return true;
		} 
		response.sendRedirect("/tasks?next="+uri);
		return false;
	}

}
