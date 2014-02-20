package com.diegolirio.tasks.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diegolirio.tasks.dao.TaskDao;
import com.diegolirio.tasks.db.TaskDB;
import com.diegolirio.tasks.model.Task;
import com.diegolirio.tasks.model.TaskItem;
import com.diegolirio.tasks.model.User;

@Controller
public class TaskController {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	private TaskDB dao;
	
	@Autowired
	public TaskController(TaskDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Locale locale, Model model, HttpSession session) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		ModelAndView mv = new ModelAndView("list_task");
		
		// Usuario
		User usuario = null;
		if (session.getAttribute("user") != null) {
			usuario = (User)session.getAttribute("user"); 
		} else {
			throw new RuntimeException("Erro: usuario não localizado !!!");
		}
		
		System.out.println("LIST========="+usuario);
		
		// Pega lista
		List<Task> tasks = this.dao.getList(usuario);
		mv.addObject("tasks", tasks);
		return mv;
	}
	
	@RequestMapping("/items")
	public ModelAndView items(Task task) {
		logger.info("Welcome home! The client locale is {}.", task.getId());
		ModelAndView mv = new ModelAndView("items");
		List<TaskItem> items = null;
		try {
			task = this.dao.getTask(task.getId());
			items = this.dao.getItems(task); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		task.setItems(items);
		mv.addObject("task", task);
		return mv;
	}
	
	@RequestMapping(value="task_form", method=RequestMethod.GET)
	public ModelAndView task_form(Task task) {
		ModelAndView mv = new ModelAndView("task_form");
		if(task != null) {
			try {
				task = this.dao.getTask(task.getId());
				mv.addObject("task", task);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return mv;
	}
	
	@RequestMapping(value="task_form", method=RequestMethod.POST)
	public String task_newupdate(@Valid Task task, BindingResult result) {
		//ModelAndView mv = new ModelAndView();
		if(result.hasFieldErrors("title")) {
			return "task_form";
		}
		String page = "";
		try {
			if(task.getId() > 0) {
				System.out.println("before update: " + task);
				System.out.println(task);
				this.dao.update(task);
			}
			else {
				System.out.println("before insert: " + task);
				this.dao.insert(task);
			//	mv.setViewName("home");
			}
			page = "redirect:/list?message=Tarefa salva com sucesso&status=N";
		} catch (Exception e) {
			//mv.setViewName("task_form");
			page = "task_form";
		}
		return page;
	}
	
	@RequestMapping(value="task_delete", method=RequestMethod.GET)
	public ModelAndView delete(Task task) {
		ModelAndView mv = new ModelAndView("task_delete");
		task = this.dao.getTask(task.getId());
		mv.addObject("task", task);
		return mv;
	}
	
	@RequestMapping(value="task_delete", method=RequestMethod.POST)
	public ModelAndView deleteDB(Task task) {
		ModelAndView mv = new ModelAndView("task_delete");
		try {
			this.dao.delete(task);
			mv.addObject("message", /*"task successfully deleted"*/ "Tarefa excluida com sucesso !!!");
			mv.addObject("status", "N");
		} catch (Exception e) {
			mv.addObject("message", e.getMessage());
			mv.addObject("status", "E");			
		}
		return mv;
	}
	
		
	
	
}
