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

import com.vsm.devcase.model.Pontuacao;
import com.vsm.devcase.service.PontuacaoService;

@RestController
@RequestMapping("pontos")
public class PontuacaoResource {
	
	@Autowired
	private PontuacaoService service;
	
	@GetMapping
	public ResponseEntity<List<Pontuacao>> listarPontuacaos() {
		List<Pontuacao> pontos = service.listarTodasPontuacoes();
		return ResponseEntity.ok().body(pontos);
	}
	
	@PostMapping
	public ResponseEntity<Pontuacao> novaPontuacao(@Valid @RequestBody Pontuacao pontuacao) {
		Pontuacao pontuacaoSalva = service.salvarNovaPontuacao(pontuacao);
		return ResponseEntity.ok().body(pontuacaoSalva);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirPontuacao(@PathVariable Long id) {
		service.excluirPontuacao(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPontuacaoPorId(@PathVariable Long id) {
		Optional<Pontuacao> pontuacao = service.buscarPontuacaoPorId(id);
		return ResponseEntity.of(pontuacao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pontuacao> atualizarPontuacao(@PathVariable Long id, @Valid @RequestBody Pontuacao pontuacao) {
		Pontuacao pontuacaoAtualizada = service.atualizarPontuacao(id, pontuacao);
		return ResponseEntity.ok().body(pontuacaoAtualizada);
	}
	
}
