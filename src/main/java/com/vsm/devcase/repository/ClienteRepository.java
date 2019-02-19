package com.vsm.devcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.devcase.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
