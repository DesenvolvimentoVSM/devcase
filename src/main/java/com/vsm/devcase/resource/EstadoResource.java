package com.vsm.devcase.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.model.Estado;
import com.vsm.devcase.service.EstadoService;

@RestController
@RequestMapping("estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@GetMapping
	public ResponseEntity<List<Estado>> listarEstados() {
		List<Estado> clientes = service.listarTodosEstados();
		return ResponseEntity.ok().body(clientes);
	}
	
	@PostMapping
	// @PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Estado> novoEstado(@Valid @RequestBody Estado estado) {
		Estado estadoSalvo = service.salvarNovoEstado(estado);
		return ResponseEntity.ok().body(estadoSalvo);
	}
	
	@DeleteMapping("/{id}")
	// @PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> excluirEstado(@PathVariable Long id) {
		service.excluirEstado(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarEstadoPorId(@PathVariable Long id) {
		Optional<Estado> estado = service.buscarEstadoPorId(id);
		return ResponseEntity.of(estado);
	}
	
	@PutMapping("/{id}")
	// @PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Estado> atualizarEstado(@PathVariable Long id, @Valid @RequestBody Estado estado) {
		Estado estadoAtualizado = service.atualizarEstado(id, estado);
		return ResponseEntity.ok().body(estadoAtualizado);
	}
	
}
