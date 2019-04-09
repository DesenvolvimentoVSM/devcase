package com.vsm.devcase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.model.Cliente;
import com.vsm.devcase.repository.ClienteRepository;
import com.vsm.devcase.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public List<Cliente> listarTodosClientes() {
		return repository.findAll();
	}
	
	public Cliente salvarNovoCliente(Cliente cliente) {
		return repository.save(cliente);
	}
	
	public void excluirCliente(Long id) {
		Optional<Cliente> cliente = buscarClientePorId(id);
		repository.deleteById(cliente.get().getId());
	}
	
	public Optional<Cliente> buscarClientePorId(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));
		return cliente;
	}
	
	public Cliente atualizarCliente(Long id, Cliente cliente) {
		Cliente clienteSalvo = repository.getOne(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return repository.save(clienteSalvo);
	}
	
	public void atualizarPontos(Long id, Cliente cliente) {
		Cliente clienteSalvo = repository.getOne(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		repository.save(clienteSalvo);
	}
	
}
