package com.gft.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Linguagem;
import com.gft.projeto.repositories.LinguagemRepository;


@Service
public class LinguagemService {
	
	@Autowired
	private LinguagemRepository linguagemRepository;
	
	public Linguagem salvarLinguagem(Linguagem linguagem) {
		return linguagemRepository.save(linguagem);
	}
	
	public List<Linguagem> listarLinguagens(String nome){
		if(nome != null) {
			 return listarPorNome(nome);
		}
		return listarTodasLinguagens();
	}
	
	 private List<Linguagem> listarPorNome(String nome){ 
		return linguagemRepository.findByNomeContains(nome);
	}
	
	public List<Linguagem> listarTodasLinguagens(){
		return linguagemRepository.findAll();
	}
	
	public Linguagem obterLinguagem(Long id) {
		return linguagemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Linguagem não encontrada."));
	}

	public void excluirLinguagem(Long id) {
		Linguagem linguagem = linguagemRepository.findById(id)
		.orElseThrow(() -> new RuntimeException("Linguagem não encontrada."));
		
		if(linguagem != null) {
			linguagemRepository.delete(linguagem);
		}
		
	}

}
