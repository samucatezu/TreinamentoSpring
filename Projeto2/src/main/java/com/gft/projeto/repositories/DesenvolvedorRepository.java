package com.gft.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.projeto.entities.Desenvolvedor;

@Repository
public interface DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Long>{
	
}