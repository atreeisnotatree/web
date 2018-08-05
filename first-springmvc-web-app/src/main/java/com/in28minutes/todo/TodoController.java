package com.in28minutes.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@RequestMapping(value = "/list-todos", method= RequestMethod.GET)
	
	public String listTodos(ModelMap model){
		model.addAttribute("todos", service.retrieveTodos(retrieveLoggedinUserName()));
		return"list-todos";
	}
	
	private String retrieveLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
		@RequestMapping(value = "/add-todo", method= RequestMethod.GET)
		
		public String showTodoPage(ModelMap model){
			throw new RuntimeException();
//			model.addAttribute("todo", new Todo(0, retrieveLoggedinUserName(), "Default description", new Date(),
//				false));
//			return"todos";
	}
		@RequestMapping(value = "/add-todo", method= RequestMethod.POST)
		
		public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
			
			if(result.hasErrors()) {
				return"todos";
			}
			service.addTodo(retrieveLoggedinUserName(), todo.getDesc(), new Date(), false);
			model.clear();
			return"redirect:list-todos";
	}
		
		@RequestMapping(value = "/update-todo", method= RequestMethod.GET)
		
		public String updateTodo(ModelMap model, @RequestParam int id){
			Todo todo = service.retrieveTodo(id);
			model.addAttribute("todo", todo);
			return"todos";
	}
		
		@RequestMapping(value = "/update-todo", method= RequestMethod.POST)
		
		public String updateTodo(@Valid Todo todo, BindingResult result){
			if(result.hasErrors()) {
				return"todos";
			}
			
			todo.setUser(retrieveLoggedinUserName());
			service.updateTodo(todo);	
			return"redirect:list-todos";
	}

		@RequestMapping(value = "/delete-todo", method= RequestMethod.GET)
		
		public String deleteTodo(ModelMap model, @RequestParam int id){
			service.deleteTodo(id);
			
			return"redirect:list-todos";
	}
}
