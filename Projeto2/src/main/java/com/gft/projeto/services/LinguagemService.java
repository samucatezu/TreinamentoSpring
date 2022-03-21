package com.gft.projeto.services;

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

}
