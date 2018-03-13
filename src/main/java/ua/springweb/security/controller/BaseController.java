package ua.springweb.security.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.springweb.security.domain.LoginRequest;
import ua.springweb.security.domain.RegisterRequest;
import ua.springweb.security.entity.UserEntity;
import ua.springweb.security.mapper.UserMapper;
import ua.springweb.security.service.UserService;

@Controller
public class BaseController {

	@Autowired UserService userService;
	
	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	
	@GetMapping("/login")
	public String showLogin(Model model) {
//		RegisterRequest registerRequest = new RegisterRequest();
//		UserEntity entity = UserMapper.registerToUse
		
	//	model.addAttribute("userModel", new LoginRequest());
		return "login";
	}

	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("registerModel", new RegisterRequest());
		return "register";
	}
	
	@PostMapping("/register")
	public String saveUser(@ModelAttribute("registerModel") RegisterRequest request) {
		userService.saveUser(UserMapper.registerToUser(request));
		return "redirect:/login";
	}
	
	/*ROLES*/
	@GetMapping("/user")
//	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public String showUser(Principal principal, Model model) {//Principal - витягуємо інфу про юзера (в даному випадку імям виступає емайл)
		UserEntity  entity = userService.findUserByEmail(principal.getName()); //получаємо імя залогіненого юзера (повертає емейл зарегістрованого користувача)
		model.addAttribute("user", entity);//щоб після редіректу показувало ваш id і email
		return"user";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		
		return "admin";
	}
	
	@GetMapping("/products")
	public String showProducts() {
		return "products";
	}
}
