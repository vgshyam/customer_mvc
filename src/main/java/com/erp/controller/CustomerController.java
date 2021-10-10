package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.erp.doa.CustomerDoa;
import com.erp.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerDoa customerDoa;

	@PostMapping("/register")
	public RedirectView register(@ModelAttribute Customer customer, RedirectAttributes attr) {

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		try {
			customerDoa.add(customer);
			redirectView.setUrl("/");

		} catch (RuntimeException ex) {

			attr.addFlashAttribute("error", ex.getMessage());
			redirectView.setUrl("/signup");
		}
		return redirectView;
	}

	@PostMapping("/signin")
	public RedirectView signIn(@ModelAttribute Customer customer, RedirectAttributes attr) {

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		try {
			customerDoa.findByUserIdAndPassword(customer);
			redirectView.setUrl("/welcome");

		} catch (RuntimeException ex) {

			attr.addFlashAttribute("error", ex.getMessage());
			redirectView.setUrl("/signin");
		}
		return redirectView;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView editUserDetail(@PathVariable Integer id) {

		ModelAndView andView = new ModelAndView("edit");
		Customer customer = customerDoa.findById(id);
		andView.addObject("customer", customer);
		return andView;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteCustomer(@PathVariable Integer id) {
		ModelAndView andView = new ModelAndView("redirect:/users");
		customerDoa.delete(id);
		return andView;
	}
	
	@PostMapping("/update")
	public ModelAndView update(@ModelAttribute Customer customer) {

		ModelAndView andView = new ModelAndView("redirect:/users");
		customerDoa.updateUser(customer);
		return andView;
	}
}
