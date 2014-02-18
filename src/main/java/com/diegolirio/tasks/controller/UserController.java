package com.diegolirio.tasks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diegolirio.tasks.dao.UserDao;
import com.diegolirio.tasks.db.UserDB;
import com.diegolirio.tasks.model.User;

@Controller
public class UserController {
	
	private UserDB dao;
	
	@Autowired
	public UserController(UserDao dao) {
		this.dao = dao;
	}
	
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
		
		if(this.dao.login(user)) {
			session.setAttribute("user", user);
			return "redirect:"+next_page;
		} else {
			result.addError(new ObjectError("login", "Login ou senha Inv√°lidos"));
			//result.rejectValue("login", "error.login", "Login ou senha Inv√°lidos");
		}
			
		
		return "login";
	}	
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("message", "VocÍ saiu do sistema");
		session.invalidate();
		return mv;
	}
	
	@RequestMapping(value="/new_account", method=RequestMethod.GET)
	public String new_account() {
		return "new_account";
	}
	
	@RequestMapping(value="/new_account", method=RequestMethod.POST)
	public ModelAndView new_account(@Valid User user, BindingResult result, String confirmPassword) { 
		ModelAndView mv = new ModelAndView();
		System.out.println("=============================================================");
		System.out.println(user);
		System.out.println("Confirm Password: " + confirmPassword);
		System.out.println("=============================================================");
		
		if(result.hasFieldErrors("name") || result.hasFieldErrors("email") || result.hasFieldErrors("password")) {
			mv.setViewName("new_account");
		} else {
			if (user.getPassword().equals(confirmPassword)) {
				if(this.dao.ExistUserEmail(user.getEmail()) == false) {
					this.dao.insert(user);
					mv.setViewName("redirect:/list");
				}
				else {
					mv.setViewName("new_account");
					mv.addObject("message", "Email j· cadastrado em nosso sistema.");
				}
			} else {
				mv.setViewName("new_account");
				mv.addObject("confirmPasswordMessage", "Senha n„o confere...");
			}
		}		 
		return mv;
	}
	
	@RequestMapping(value="perfil", method=RequestMethod.GET)
	public String perfil() {
		return "perfil";
	}

}
