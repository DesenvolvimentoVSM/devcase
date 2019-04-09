package com.vsm.devcase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.model.Pontuacao;
import com.vsm.devcase.repository.PontuacaoRepository;
import com.vsm.devcase.service.exceptions.ObjectNotFoundException;

@Service
public class PontuacaoService {

	@Autowired
	private PontuacaoRepository repository;
	
	public List<Pontuacao> listarTodasPontuacoes() {
		return repository.findAll();
	}
	
	public Pontuacao salvarNovaPontuacao(Pontuacao pontuacao) {
		return repository.save(pontuacao);
	}
	
	public void excluirPontuacao(Long id) {
		Optional<Pontuacao> pontuacao = buscarPontuacaoPorId(id);
		repository.deleteById(pontuacao.get().getId());
	}
	
	public Optional<Pontuacao> buscarPontuacaoPorId(Long id) {
		Optional<Pontuacao> pontuacao = repository.findById(id);
		pontuacao.orElseThrow(() -> new ObjectNotFoundException("Pontuação não encontrada!"));
		return pontuacao;
	}
	
	public Pontuacao atualizarPontuacao(Long id, Pontuacao pontuacao) {
		Pontuacao pontuacaoSalva = repository.getOne(id);
		BeanUtils.copyProperties(pontuacao, pontuacaoSalva, "id");
		return repository.save(pontuacaoSalva);
	}
	
}
