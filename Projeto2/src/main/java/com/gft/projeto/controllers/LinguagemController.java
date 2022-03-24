package com.gft.projeto.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.projeto.entities.Linguagem;
import com.gft.projeto.services.LinguagemService;



@Controller
@RequestMapping("linguagem")
public class LinguagemController {
	
	@Autowired
	LinguagemService linguagemService;
	

	@RequestMapping(path = "novo")
	public ModelAndView novaLinguagem() {
		
		ModelAndView mv = new ModelAndView("linguagem/form.html");
		mv.addObject("linguagem", new Linguagem());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarLinguagem(@Valid Linguagem linguagem, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("linguagem/form.html");
		
		boolean novo = true;
		
		if(linguagem.getId() != null) {
			novo = false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("linguagem", linguagem);
			return mv;
		}
		
		Linguagem linguagemSalva = linguagemService.salvarLinguagem(linguagem);
		
		if(novo) {
			mv.addObject("linguagem", new Linguagem());
		}else {
			mv.addObject("linguagem", linguagemSalva);
		}
			
		mv.addObject("message", "Registro salvo com sucesso");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarLinguagem(String nome){
		ModelAndView mv = new ModelAndView("linguagem/listar.html");
		mv.addObject("lista", linguagemService.listarLinguagens(nome));
		
		return mv;
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarLinguagem(@RequestParam Long id) {
		ModelAndView mv = new ModelAndView("linguagem/form.html");
		Linguagem linguagem;
		try {
			linguagem = linguagemService.obterLinguagem(id);
		} catch (Exception e) {
			linguagem = new Linguagem();
			mv.addObject("message", e.getMessage());
		}
		
		mv.addObject("linguagem", linguagem);
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirLinguagem(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/linguagem");

		try {
			linguagemService.excluirLinguagem(id);
			redirectAttributes.addFlashAttribute("message", "Registro Excluído com sucesso.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir registro."+e.getMessage());
		}
		
		return mv;
	}

}
