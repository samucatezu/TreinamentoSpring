package com.gft.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Usuario;
import com.gft.projeto.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario efetuarLogin(Usuario usuario) {
		return usuarioRepository.findByNomeAndSenha(usuario.getNome(), usuario.getSenha())
				.orElseThrow(()-> new RuntimeException("Usuario ou Senha inválido"));
	}
}
