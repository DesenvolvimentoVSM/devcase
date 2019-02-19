package com.vsm.devcase.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vsm.devcase.model.Cliente;
import com.vsm.devcase.model.Pontuacao;
import com.vsm.devcase.model.Venda;
import com.vsm.devcase.repository.VendaRepository;
import com.vsm.devcase.service.exceptions.ObjectNotFoundException;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PontuacaoService pontuacaoService;
	
	public List<Venda> listarVendas(String dataVendaDe, String dataVendaAte, String sexo) {
		List<Venda> vendas;
		
		if (StringUtils.isEmpty(dataVendaDe) && StringUtils.isEmpty(dataVendaAte) && StringUtils.isEmpty(sexo)) {
			vendas = repository.findAll();
		} else if (StringUtils.isEmpty(sexo)) {
			vendas = repository.findByDataBetween(dataVendaDe, dataVendaAte);
		} else {
			vendas = repository.findByDataVendaDeAndDataVendaAteAndSexo(dataVendaDe, dataVendaAte, sexo);
		}
		
		return vendas;
	}
	
	public Venda salvarNovaVenda(Venda venda) {
		Venda vendaSalva = new Venda();
		try {
			List<Pontuacao> pontuacoes = pontuacaoService.listarTodasPontuacoes();
			for (Pontuacao pontos : pontuacoes) {
				BigDecimal valorVenda = venda.getValor();
				if (valorVenda.compareTo(pontos.getValorInicio()) >= 1 && valorVenda.compareTo(pontos.getValorFinal()) <= -1 ) {
					venda.setPontos(pontos);
				}
			}
			vendaSalva = repository.save(venda);
			
			atualizarPontosCliente(vendaSalva);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vendaSalva;
	}
	
	public Cliente buscarClientePelaVenda(Venda venda) {
		Optional<Cliente> cliente = clienteService.buscarClientePorId(venda.getCliente().getId());
		return cliente.get();
	}
	
	public void atualizarPontosCliente(Venda venda) {
		Cliente cliente = buscarClientePelaVenda(venda);
		int pontosAtuais = cliente.getTotalPontos();
		cliente.setTotalPontos(pontosAtuais + venda.getPontos().getPontos());
		clienteService.atualizarPontos(cliente.getId(), cliente);
	}
	
	public void excluirVenda(Long id) {
		Optional<Venda> venda = buscarVendaPorId(id);
		Cliente cliente = buscarClientePelaVenda(venda.get());
		int pontosAtuais = cliente.getTotalPontos();
		cliente.setTotalPontos(pontosAtuais - venda.get().getPontos().getPontos());
		repository.deleteById(venda.get().getId());
		clienteService.atualizarPontos(cliente.getId(), cliente);
	}
	
	public Optional<Venda> buscarVendaPorId(Long id) {
		Optional<Venda> venda = repository.findById(id);
		venda.orElseThrow(() -> new ObjectNotFoundException("Venda não encontrada!"));
		return venda;
	}
	
}
