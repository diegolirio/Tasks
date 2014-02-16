package com.diegolirio.tasks.controller;

import java.sql.Connection;
import java.util.List;
import java.util.Locale;

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
import com.diegolirio.tasks.dao.TaskItemDao;
import com.diegolirio.tasks.jdbc.FactoryConnection;
import com.diegolirio.tasks.model.Task;
import com.diegolirio.tasks.model.TaskItem;
import com.diegolirio.tasks.model.User;

@Controller
public class TaskController {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	private TaskDao dao;
	
	public TaskController() {
		//this.dao = dao;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		ModelAndView mv = new ModelAndView("list_task");
		// Usuario
		User usuario = new User();
		usuario.setId(1);
		// Pega lista
		Connection conn = new FactoryConnection().getConnection();
		List<Task> tasks = new TaskDao(conn).getList(usuario);
		mv.addObject("tasks", tasks);
		return mv;
	}
	
	@RequestMapping("/items")
	public ModelAndView items(Task task) {
		logger.info("Welcome home! The client locale is {}.", task.getId());
		ModelAndView mv = new ModelAndView("items");
		List<TaskItem> items = null;
		try {
			Connection conn = new FactoryConnection().getConnection();
			task = new TaskDao(conn).getTask(task.getId());
			items = new TaskDao(conn).getItems(task); 
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
				Connection conn = new FactoryConnection().getConnection();
				task = new TaskDao(conn).getTask(task.getId());
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
				Connection conn = new FactoryConnection().getConnection();
				new TaskDao(conn).update(task);
			}
			else {
				System.out.println("before insert: " + task);
				Connection conn = new FactoryConnection().getConnection();
				new TaskDao(conn).insert(task);
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
		Connection conn = new FactoryConnection().getConnection();
		task = new TaskDao(conn).getTask(task.getId());
		mv.addObject("task", task);
		return mv;
	}
	
	@RequestMapping(value="task_delete", method=RequestMethod.POST)
	public ModelAndView deleteDB(Task task) {
		ModelAndView mv = new ModelAndView("task_delete");
		try {
			Connection conn = new FactoryConnection().getConnection();
			new TaskDao(conn).delete(task);
			mv.addObject("message", /*"task successfully deleted"*/ "Tarefa exclu�da com sucesso !!!");
			mv.addObject("status", "N");
		} catch (Exception e) {
			mv.addObject("message", e.getMessage());
			mv.addObject("status", "E");			
		}
		return mv;
	}
	
	@RequestMapping(value="items/cad_item", method=RequestMethod.GET)
	public ModelAndView itemForm(TaskItem item) {
		ModelAndView mv = new ModelAndView("add_item");
		if(item.getId() > 0) 
			item = new TaskItemDao().getTaskItem(item.getId());
		else {
			Connection conn = new FactoryConnection().getConnection();
			item.setTask(new TaskDao(conn).getTask(item.getTask().getId()));
		}
	
		mv.addObject("item",item);
		return mv;
	}
	
	@RequestMapping(value="items/cad_item", method=RequestMethod.POST)
	public String itemFormSave(@Valid TaskItem item, BindingResult result) {
		if(result.hasFieldErrors("description")) {
			logger.info(result.getFieldError("description").getDefaultMessage());
			return "add_item";
		}		
		String page = "";
		try { 
			System.out.println(item);
			if (item.getId() == 0)
				new TaskItemDao().insert(item);
			else
				new TaskItemDao().update(item);			
			page = "redirect:/items/?id="+item.getTask().getId();  
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;		
	}
	
	@RequestMapping(value="items/item_delete", method=RequestMethod.GET)
	public ModelAndView delete(TaskItem item) {
		ModelAndView mv = new ModelAndView("item_delete");
		item = new TaskItemDao().getTaskItem(item.getId());
		mv.addObject("item", item);
		return mv;
	}
	
	@RequestMapping(value="items/item_delete", method=RequestMethod.POST)
	public ModelAndView deleteDB(TaskItem item) {
		ModelAndView mv = new ModelAndView("item_delete");
		try {
			new TaskItemDao().delete(item);
			mv.addObject("message", "Item exclu�da com sucesso !!!");
			mv.addObject("status", "N");
		} catch (Exception e) {
			mv.addObject("message", e.getMessage());
			mv.addObject("status", "E");			
		}
		return mv;
	}	
	
	
}
