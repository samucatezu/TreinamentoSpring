package com.gft.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gft.projeto.entities.Desenvolvedor;
import com.gft.projeto.services.DesenvolvedorService;
import com.gft.projeto.services.LinguagemService;

@Controller
@RequestMapping("desenvolvedor")

public class DesenvolvedorController {
	
	@Autowired
    private DesenvolvedorService desenvolvedorService;
	
	@Autowired
    private LinguagemService linguagemService;
    
   @RequestMapping (path = "editar")
    public ModelAndView editarDesenvolvedor (@RequestParam(required = false) Long id) {
	   
        ModelAndView mv = new ModelAndView("desenvolvedor/form.html");
        
        Desenvolvedor desenvolvedor;
        
        if(id==null) {
           desenvolvedor = new Desenvolvedor();
        }else { 
        	try {
        		desenvolvedor = desenvolvedorService.obterDesenvolvedor(id);
        	}catch(Exception e) {
        		desenvolvedor = new Desenvolvedor();
        		mv.addObject("mensagem", e.getMessage());
        	}
        }
        
        mv.addObject("desenvolvedor", desenvolvedor);
        mv.addObject("listaLinguagens", linguagemService.listarLinguagem());
        
        return mv;
        
   }
}
   