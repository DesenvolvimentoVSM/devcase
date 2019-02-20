package com.vsm.devcase.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.devcase.model.Venda;
import com.vsm.devcase.model.enums.Sexo;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	List<Venda> findByDataBetween(LocalDate dataVendaDe, LocalDate dataVendaAte);

	List<Venda> findByDataBetweenAndClienteSexo(LocalDate dataVendaDe, LocalDate dataVendaAte, Sexo sexo);
	
}
