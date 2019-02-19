package com.vsm.devcase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.model.TipoPagamento;
import com.vsm.devcase.repository.TipoPagamentoRepository;
import com.vsm.devcase.service.exceptions.ObjectNotFoundException;

@Service
public class TipoPagamentoService {

	@Autowired
	private TipoPagamentoRepository repository;
	
	public List<TipoPagamento> listarTodosTipoPagamentos() {
		return repository.findAll();
	}
	
	public TipoPagamento salvarNovoTipoPagamento(TipoPagamento tipo) {
		return repository.save(tipo);
	}
	
	public void excluirTipoPagamento(Long id) {
		Optional<TipoPagamento> tipo = buscarTipoPagamentoPorId(id);
		repository.deleteById(tipo.get().getId());
	}
	
	public Optional<TipoPagamento> buscarTipoPagamentoPorId(Long id) {
		Optional<TipoPagamento> tipo = repository.findById(id);
		tipo.orElseThrow(() -> new ObjectNotFoundException("Tipo de pagamento não encontrado!"));
		return tipo;
	}
	
	public TipoPagamento atualizarTipoPagamento(Long id, TipoPagamento tipo) {
		TipoPagamento tipoSalvo = repository.getOne(id);
		BeanUtils.copyProperties(tipo, tipoSalvo, "id");
		return repository.save(tipoSalvo);
	}
	
}
