package com.sincco.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SegurancaController {

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/apresentacao";
		}
		return "login";
	}
	
	@GetMapping("/")
	public String login2(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/apresentacao";
		}
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginAction(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/apresentacao";
		}
		return "login";
	}
	
	@GetMapping("/403")
	public String acessoNegado() {
		return "403";
	}
	
}
