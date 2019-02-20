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

import com.vsm.devcase.model.TipoPagamento;
import com.vsm.devcase.service.TipoPagamentoService;

@RestController
@RequestMapping("tipo-pagamento")
public class TipoPagamentoResource {
	
	@Autowired
	private TipoPagamentoService service;
	
	@GetMapping
	public ResponseEntity<List<TipoPagamento>> listarTipoPagamentos() {
		List<TipoPagamento> tipos = service.listarTodosTipoPagamentos();
		return ResponseEntity.ok().body(tipos);
	}
	
	@PostMapping
	// @PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<TipoPagamento> novoTipoPagamento(@Valid @RequestBody TipoPagamento tipo) {
		TipoPagamento tipoSalvo = service.salvarNovoTipoPagamento(tipo);
		return ResponseEntity.ok().body(tipoSalvo);
	}
	
	@DeleteMapping("/{id}")
	// @PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> excluirTipoPagamento(@PathVariable Long id) {
		service.excluirTipoPagamento(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarTipoPagamentoPorId(@PathVariable Long id) {
		Optional<TipoPagamento> tipo = service.buscarTipoPagamentoPorId(id);
		return ResponseEntity.of(tipo);
	}
	
	@PutMapping("/{id}")
	// @PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<TipoPagamento> atualizarTipoPagamento(@PathVariable Long id, @Valid @RequestBody TipoPagamento tipo) {
		TipoPagamento tipoAtualizado = service.atualizarTipoPagamento(id, tipo);
		return ResponseEntity.ok().body(tipoAtualizado);
	}

}
