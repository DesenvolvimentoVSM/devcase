package com.vsm.devcase.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.model.Cliente;
import com.vsm.devcase.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> clientes = service.listarTodosClientes();
		return ResponseEntity.ok().body(clientes);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> novoCliente(@Valid @RequestBody Cliente cliente) {
		Cliente clienteSalvo = service.salvarNovoCliente(cliente);
		return ResponseEntity.ok().body(clienteSalvo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
		service.excluirCliente(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
		Optional<Cliente> cliente = service.buscarClientePorId(id);
		return ResponseEntity.of(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = service.atualizarCliente(id, cliente);
		return ResponseEntity.ok().body(clienteAtualizado);
	}
	
}
