package com.diegolirio.tasks.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diegolirio.tasks.dao.TaskItemDao;
import com.diegolirio.tasks.db.TaskItemDB;
import com.diegolirio.tasks.model.TaskItem;

//@Controller
public class TaskItemController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	private TaskItemDB dao;
	
	//@Autowired
	public TaskItemController(TaskItemDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="items/cad_item_ANALISAR", method=RequestMethod.GET)
	public ModelAndView itemForm(TaskItem item) {
		ModelAndView mv = new ModelAndView("add_item");
		if(item.getId() > 0) 
			item = this.dao.getTaskItem(item.getId());
		else {
			//item.setTask(this.dao.getTask(item.getTask().getId()));
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
		item = this.dao.getTaskItem(item.getId());
		mv.addObject("item", item);
		return mv;
	}	
	
	@RequestMapping(value="items/item_delete", method=RequestMethod.POST)
	public ModelAndView deleteDB(TaskItem item) {
		ModelAndView mv = new ModelAndView("item_delete");
		try {
			this.dao.delete(item);
			mv.addObject("message", "Item exclu�da com sucesso !!!");
			mv.addObject("status", "N");
		} catch (Exception e) {
			mv.addObject("message", e.getMessage());
			mv.addObject("status", "E");			
		}
		return mv;
	}		
	
}
