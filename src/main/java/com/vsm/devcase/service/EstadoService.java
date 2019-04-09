package com.vsm.devcase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.model.Estado;
import com.vsm.devcase.repository.EstadoRepository;
import com.vsm.devcase.service.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	public List<Estado> listarTodosEstados() {
		return repository.findAll();
	}
	
	public Estado salvarNovoEstado(Estado estado) {
		return repository.save(estado);
	}
	
	public void excluirEstado(Long id) {
		Optional<Estado> estado = buscarEstadoPorId(id);
		repository.deleteById(estado.get().getId());
	}
	
	public Optional<Estado> buscarEstadoPorId(Long id) {
		Optional<Estado> estado = repository.findById(id);
		estado.orElseThrow(() -> new ObjectNotFoundException("Estado não encontrado!"));
		return estado;
	}
	
	public Estado atualizarEstado(Long id, Estado estado) {
		Estado estadoSalvo = repository.getOne(id);
		BeanUtils.copyProperties(estado, estadoSalvo, "id");
		return repository.save(estadoSalvo);
	}
	
}
