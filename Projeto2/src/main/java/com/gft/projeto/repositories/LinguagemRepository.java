package com.gft.projeto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.projeto.entities.Linguagem;


@Repository
public interface LinguagemRepository extends JpaRepository<Linguagem, Long> {
	List<Linguagem> findByNomeContains(String nome);
}
