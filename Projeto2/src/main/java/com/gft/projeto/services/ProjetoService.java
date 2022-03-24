package com.gft.projeto.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.projeto.entities.Projeto;
import com.gft.projeto.repositories.ProjetoRepository;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;

	public Projeto salvarProjeto(Projeto projeto) {

		return projetoRepository.save(projeto);
	}

	public List<Projeto> listarProjeto() {
		return projetoRepository.findAll();
	}
	
	public List<Projeto> listarProjetos(String nome, String dataEntrega) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(nome != null && dataEntrega == "") {
			 return listarPorNome(nome); 
		}else if(nome == "" && dataEntrega != null) {
			Date data = format.parse(dataEntrega);
			return listarPorDataEntrega(data);
		}
		else if(nome != null && dataEntrega !=null) {
			Date data = format.parse(dataEntrega);
			return projetoRepository.findByNomeAndDataEntrega(nome, data);
		}
		return listarTodosProjetos();
	}
	
	private List<Projeto> listarPorDataEntrega(Date dataEntrega){ 
		return projetoRepository.findByDataEntrega(dataEntrega);
	}
	
	 private List<Projeto> listarPorNome(String nome){ 
		return projetoRepository.findByNomeContains(nome);
	}
	
	public List<Projeto> listarTodosProjetos(){
		return projetoRepository.findAll();
	}

	public Projeto obterProjeto(Long id) {
		return projetoRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}

	public void excluirProjeto(Long id) {
		Projeto projeto = projetoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Linguagem não encontrada."));

		if (projeto != null) {
			projetoRepository.delete(projeto);
		}

	}

}
