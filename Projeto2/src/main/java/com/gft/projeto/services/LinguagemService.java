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
	
	public List<Linguagem> listarLinguagem(){
		
		   return linguagemRepository.findAll();
	}
	
	public Linguagem obterlinguagem(Long id) throws Exception {
		
	
	    Optional<Linguagem> linguagem = linguagemRepository.findById(id);
	    
	    if(linguagem.isEmpty()) {
	    	
	    
	       throw new Exception("Linguagem não encontrada.");
	    }
	       
	    return linguagem.get();
	    }

	public void excluirLinguagem(Long id) {
		linguagemRepository.deleteById(id);
	}

}
