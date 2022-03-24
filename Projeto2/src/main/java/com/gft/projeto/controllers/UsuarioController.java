package com.gft.projeto.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.projeto.entities.Usuario;
import com.gft.projeto.services.UsuarioService;



@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@RequestMapping(path = "novo")
	public ModelAndView novoUsuario() {

		ModelAndView mv = new ModelAndView("usuario/form.html");
		mv.addObject("usuario", new Usuario());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("usuario/form.html");

		boolean novo = true;

		if (usuario.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("usuario", usuario);
			return mv;
		}

		usuarioService.salvarUsuario(usuario);

		if (novo) {
			mv.addObject("usuario", new Usuario());
		} else {
			mv.addObject("usuario", usuario);
		}

		mv.addObject("message", "Registro salvo com sucesso");

		return mv;
	}
	


}
