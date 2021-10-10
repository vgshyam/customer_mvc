package com.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.erp.doa.CustomerDoa;
import com.erp.model.Customer;

@Controller
public class IndexController {

	@Autowired
	private CustomerDoa customerDoa;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/users")
	public ModelAndView displayUsers() {

		ModelAndView andView = new ModelAndView("users");
		List<Customer> customers = customerDoa.allUsers();
		andView.addObject("customers", customers);
		return andView;
	}

	
}
