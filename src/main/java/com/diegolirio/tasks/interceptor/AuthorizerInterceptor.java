package com.diegolirio.tasks.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorizerInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		//System.out.println(uri);
		//String next_page = request.getParameter("next") == null || "".equals(request.getParameter("next")) ? "/list" : request.getParameter("next");
		//System.out.println("NEXT "+next_page);		
		
		// Se estiver Logado redireciona para pagina de Lista de tarefas
		if(request.getSession().getAttribute("user") != null && (uri.endsWith("tasks/") || uri.endsWith("tasks"))) {
			response.sendRedirect("/tasks/list");
			return false;
		}
		
		
		if(uri.endsWith("tasks/") || uri.endsWith("tasks") || uri.contains("/static/") || uri.endsWith("/new_account/")) {
			return true;
		}
		
		if(request.getSession().getAttribute("user") != null) {
			return true; 
		} 
		response.sendRedirect("/tasks?next="+uri);
		return false;
	}

}
