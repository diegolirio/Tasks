package com.diegolirio.tasks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.diegolirio.tasks.dao.UserDao;
import com.diegolirio.tasks.model.User;

@Controller
public class UserController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String login(@Valid User user, BindingResult result, HttpSession session, HttpServletRequest request) {
		
		System.out.println(user);
		
		if(result.hasFieldErrors("email") || result.hasFieldErrors("password")) {
			return "login";
		} 
		
		//String pnext = request.getParameter("next");
		String next_page = "/list"; //pnext == null || "".equals(pnext) ? "/list" : pnext;
		System.out.println("NEXT_PAGE: " + next_page);
		
		if(new UserDao().login(user)) {
			session.setAttribute("user", user);
			return "redirect:"+next_page;
		} else {
			result.addError(new ObjectError("login", "Login ou senha Inválidos"));
			//result.rejectValue("login", "error.login", "Login ou senha Inválidos");
		}
			
		
		return "login";
	}	
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@RequestMapping(value="perfil", method=RequestMethod.GET)
	public String perfil() {
		return "perfil";
	}

}
