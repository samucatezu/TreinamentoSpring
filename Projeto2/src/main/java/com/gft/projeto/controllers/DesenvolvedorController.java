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

import com.gft.projeto.entities.Desenvolvedor;
import com.gft.projeto.services.DesenvolvedorService;
import com.gft.projeto.services.LinguagemService;



@Controller
@RequestMapping("desenvolvedor")
public class DesenvolvedorController {
	
	@Autowired
	LinguagemService linguagemService;
	
	@Autowired
	DesenvolvedorService desenvolvedorService;
	
	
	@RequestMapping(path = "editar")
	public ModelAndView editarDesevolvedor(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("desenvolvedor/form.html");
		
		Desenvolvedor desenvolvedor;
		
		if (id == null) {
			desenvolvedor = new Desenvolvedor();
		}else {
			try {
				desenvolvedor = desenvolvedorService.obterDesenvolvedor(id);
			} catch (Exception e) {
				desenvolvedor = new Desenvolvedor();
				mv.addObject("message", e.getMessage());
			}
		}
		mv.addObject("desenvolvedor", desenvolvedor);
		mv.addObject("listaLinguagens", linguagemService.listarTodasLinguagens());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarDesenvolvedor(@Valid Desenvolvedor desenvolvedor, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("desenvolvedor/form.html");
		
		boolean novo = true;
		
		if(desenvolvedor.getId() != null) {
			novo = false;
		} 
		
		if(bindingResult.hasErrors()) {
			mv.addObject("desenvolvedor", desenvolvedor);
			return mv;
		}
		
		desenvolvedorService.salvarDesenvolvedor(desenvolvedor);
		
		if(novo) {
			mv.addObject("desenvolvedor", new Desenvolvedor());
		}else {
			mv.addObject("desenvolvedor", desenvolvedor);
		}
			
		mv.addObject("message", "Registro salvo com sucesso");
		mv.addObject("listaLinguagens", linguagemService.listarTodasLinguagens());
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarDesenvolvedor(String nome){
		ModelAndView mv = new ModelAndView("desenvolvedor/listar.html");
		mv.addObject("lista", desenvolvedorService.listarDesenvolvedor(nome));
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirDesenvolvedor(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/desenvolvedor");

		try {
			desenvolvedorService.excluirDesenvolvedor(id);
			redirectAttributes.addFlashAttribute("message", "Registro Exclu??do com sucesso.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir registro."+e.getMessage());
		}
		
		return mv;
	}

}
   