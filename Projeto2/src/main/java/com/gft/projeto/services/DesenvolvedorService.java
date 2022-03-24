package com.gft.projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Desenvolvedor;
import com.gft.projeto.repositories.DesenvolvedorRepository;

@Service
public class DesenvolvedorService {
	
	@Autowired
	private DesenvolvedorRepository desenvolvedorRepository;

	public Desenvolvedor salvarDesenvolvedor(Desenvolvedor desenvolvedor) {
		
		return desenvolvedorRepository.save(desenvolvedor);
	}
	
	public List<Desenvolvedor> listarTodosDesenvolvedores(){
		return desenvolvedorRepository.findAll();
	}
	
	public List<Desenvolvedor> listarDesenvolvedor(String nome){
		if(nome != null) {
			 return listarPorNome(nome);
		}
		return listarTodosDesenvolvedores();
	}
	
	 private List<Desenvolvedor> listarPorNome(String nome){ 
		return desenvolvedorRepository.findByNomeContains(nome);
	}
	
	public Desenvolvedor obterDesenvolvedor(Long id) {
		return desenvolvedorRepository.findById(id)
				.orElseThrow(()-> new RuntimeException());
	}
	
	public void excluirDesenvolvedor(Long id) {
		Desenvolvedor desenvolvedor = desenvolvedorRepository.findById(id)
		.orElseThrow(() -> new RuntimeException("Linguagem não encontrada."));
		
		if(desenvolvedor != null) {
			desenvolvedorRepository.delete(desenvolvedor);
		}
		
	}

}
