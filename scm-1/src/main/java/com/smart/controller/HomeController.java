package com.smart.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class HomeController {
	@Autowired
	  private UserRepository userRepository;
	@RequestMapping ("/home")
	public String home(Model model)
	
	{
		model.addAttribute("title","Home-Smart Contact Manager");
		return"home";
		
	}
	@RequestMapping ("/about")
	public String about(Model model)
	
	{
		model.addAttribute("title","About-Smart Contact Manager");
		return"about";
		
	}
	
	@RequestMapping ("/signup")
	public String signup(Model model)
	
	{
		model.addAttribute("title","signup-Smart Contact Manager");
		model.addAttribute("user", new User());
		return"signup";
		
	}
	@PostMapping ("/do_register")
	public String registeruser(@Valid   @ModelAttribute("user") User user,@RequestParam(value = "aggrement",defaultValue="false") boolean aggrement,Model model,BindingResult result1,HttpSession session )
	
	{ try {if( !aggrement) {
		
		System.out.println("you have not agreed terma and condition");
 	}
	if(result1.hasErrors())
	{
		model.addAttribute("user",user); 
		return"signup";
	}
	
	
	user.setRole("Role_user");
	user.setEnabled(true);
	user.setImageUrl("deafult.png");
	
System.out.println("Aggrement"+aggrement);
System.out.println("USER"+user);
this.userRepository.save(user);
model.addAttribute("user",new User());


model.addAttribute("user",user);
	
	}
	
	catch(Exception e) {
		e.printStackTrace();
		model.addAttribute("user",user);
		
	}
	
	
	
	
	
		
		return"signup";
		
	}

}
