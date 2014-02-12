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
import com.diegolirio.tasks.model.TaskItems;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		ModelAndView mv = new ModelAndView("home");
		List<Task> tasks = new TaskDao().getAllList();
		mv.addObject("tasks", tasks);
		return mv;
	}
	
	@RequestMapping("items")
	public ModelAndView items(Task task) {
		logger.info("Welcome home! The client locale is {}.", task.getId());
		ModelAndView mv = new ModelAndView("items");
		List<TaskItems> items = null;
		try {
			items = new TaskDao().getItems(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		task.setItems(items);
		mv.addObject("task", task);
		return mv;
	}
	
}
