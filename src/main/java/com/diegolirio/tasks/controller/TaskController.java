package com.diegolirio.tasks.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diegolirio.tasks.dao.TaskDao;
import com.diegolirio.tasks.model.Task;
import com.diegolirio.tasks.model.TaskItem;
import com.diegolirio.tasks.model.Usuario;

@Controller
public class TaskController {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		ModelAndView mv = new ModelAndView("home");
		// Usuario
		Usuario usuario = new Usuario();
		usuario.setId(1);
		// Pega lista
		List<Task> tasks = new TaskDao().getList(usuario);
		mv.addObject("tasks", tasks);
		return mv;
	}
	
	@RequestMapping("/items")
	public ModelAndView items(Task task) {
		logger.info("Welcome home! The client locale is {}.", task.getId());
		ModelAndView mv = new ModelAndView("items");
		List<TaskItem> items = null;
		try {
			items = new TaskDao().getItems(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		task.setItems(items);
		mv.addObject("task", task);
		return mv;
	}
	
	@RequestMapping(value="task_form", method=RequestMethod.GET)
	public ModelAndView task_form(Task task) {
		ModelAndView mv = new ModelAndView("task_form");		
		return mv;
	}
	
	@RequestMapping(value="task_form", method=RequestMethod.POST)
	public ModelAndView task_newupdate(Task task) {
		ModelAndView mv = new ModelAndView();
		System.out.println("before insert: " + task);
		try {
			new TaskDao().insertTask(task);
			mv.setViewName("home");
		} catch (Exception e) {
			mv.setViewName("task_form");
		}
		return mv;
	}
	
}
