package com.vsm.devcase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.model.Cidade;
import com.vsm.devcase.repository.CidadeRepository;
import com.vsm.devcase.service.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	public List<Cidade> listarTodasCidades() {
		return repository.findAll();
	}
	
	public Cidade salvarNovaCidade(Cidade cidade) {
		return repository.save(cidade);
	}
	
	public void excluirCidade(Long id) {
		Optional<Cidade> cidade = buscarCidadePorId(id);
		repository.deleteById(cidade.get().getId());
	}
	
	public Optional<Cidade> buscarCidadePorId(Long id) {
		Optional<Cidade> cidade = repository.findById(id);
		cidade.orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada!"));
		return cidade;
	}
	
	public Cidade atualizarCidade(Long id, Cidade cidade) {
		Cidade cidadeSalva = repository.getOne(id);
		BeanUtils.copyProperties(cidade, cidadeSalva, "id");
		return repository.save(cidadeSalva);
	}
	
}
