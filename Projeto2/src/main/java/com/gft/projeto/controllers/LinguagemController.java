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
	private LinguagemService linguagemService;
	
	@RequestMapping(path = "novo")
	public ModelAndView novaLinguagem() {
		ModelAndView mv = new ModelAndView("linguagem/form.html");
				mv.addObject("linguagem", new Linguagem());
				
				return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarlinguagem(@Valid Linguagem linguagem, BindingResult bindingResult) {
		
	
		ModelAndView mv = new ModelAndView("linguagem/form.html");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("linguagem", linguagem);
			return mv;
		}
		
		Linguagem linguagemSalva = linguagemService.salvarLinguagem(linguagem);
		
		if(linguagem.getId()==null) {
			mv.addObject("linguagem", new Linguagem());
		}else {
			mv.addObject("linguagem", linguagemSalva);
		}
	    
	    mv.addObject("mensagem", "Linguagem salva com sucesso");

	    return mv;
	    
	}
	
	@RequestMapping // http://localhost:8030/linguagem
	public ModelAndView listarlinguagens () {
		
		ModelAndView mv = new ModelAndView("linguagem/listar.html");
		mv.addObject("lista", linguagemService.listarLinguagem());

		return mv;
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarlinguagem(@RequestParam Long id) {
	                                                     
	    ModelAndView mv = new ModelAndView("linguagem/form.html");
	    Linguagem linguagem;
	    
	    try {
	       linguagem = linguagemService.obterlinguagem(id);
	    }catch(Exception e) {
	        linguagem = new Linguagem();
	        mv.addObject("mensagem", e.getMessage());
	    }
	        mv.addObject("linguagem", linguagem);
	        
	    
	    return mv;
	
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirlinguagem(@RequestParam Long id, RedirectAttributes redirectAttributes) {
	                                                     
	    ModelAndView mv = new ModelAndView("rediredct:/linguagem");
	    Linguagem linguagem;
	    
	    try {
	       linguagemService.excluirLinguagem(id);
	       redirectAttributes.addFlashAttribute("mensagem", "Linguagem excluida com sucesso");
	    }catch(Exception e) {
	    	redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir linguagem"+e.getMessage());
	    }
	        
	        
	    
	    return mv;
	
	}
}
