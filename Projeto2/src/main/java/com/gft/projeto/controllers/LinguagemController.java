package com.gft.projeto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("linguagem")
public class LinguagemController {
	
	@RequestMapping(path = "novo")
	public ModelAndView novaLinguagem() {
		ModelAndView mv = new ModelAndView("linguagem/novo.html");
				mv.addObject("linguagem", new Linguagem());
				
				return mv;
	}
}
