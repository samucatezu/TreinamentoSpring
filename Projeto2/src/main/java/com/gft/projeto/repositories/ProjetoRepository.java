package com.gft.projeto.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.projeto.entities.Linguagem;
import com.gft.projeto.entities.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

	List<Projeto> findByNomeContains(String nome);
	
	List<Projeto> findByDataEntrega(Date data);
	
	List<Projeto> findByNomeAndDataEntrega(String nome, Date data);
	
	List<Linguagem> findByLinguagem(Linguagem linguagem);
	
}
