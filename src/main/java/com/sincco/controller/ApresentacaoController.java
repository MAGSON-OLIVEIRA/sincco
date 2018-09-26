package com.sincco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ApresentacaoController {
	@RequestMapping("/apresentacao")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("apresentacao/apresentacao");
		return mv;
	}
}