package com.vsm.devcase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vsm.devcase.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	List<Venda> findByDataBetween(String dataVendaDe, String dataVendaAte);
	
	@Query(value = "select * from venda inner join cliente on venda.cliente_id = cliente.id"
			+ " where (data between ?1 and ?2) and cliente.sexo = ?3;", nativeQuery=true)
	List<Venda> findByDataVendaDeAndDataVendaAteAndSexo(String dataVendaDe,
			String dataVendaAte, String sexo);
	
	
}
