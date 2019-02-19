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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.model.Venda;
import com.vsm.devcase.service.VendaService;

@RestController
@RequestMapping("vendas")
public class VendaResource {

	@Autowired
	private VendaService service;
	
	@GetMapping
	public ResponseEntity<List<Venda>> listarVendas(@RequestParam(name = "dataDe", required=false) String dataVendaDe,
			@RequestParam(name = "dataAte", required=false) String dataVendaAte, 
			@RequestParam(name = "sexo", required=false) String sexo) {
		List<Venda> clientes = service.listarVendas(dataVendaDe, dataVendaAte, sexo);
		return ResponseEntity.ok().body(clientes);
	}
	
	@PostMapping
	public ResponseEntity<Venda> novoVenda(@Valid @RequestBody Venda venda) {
		Venda vendaSalva = service.salvarNovaVenda(venda);
		return ResponseEntity.ok().body(vendaSalva);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirVenda(@PathVariable Long id) {
		service.excluirVenda(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarVendaPorId(@PathVariable Long id) {
		Optional<Venda> venda = service.buscarVendaPorId(id);
		return ResponseEntity.of(venda);
	}
	
}
