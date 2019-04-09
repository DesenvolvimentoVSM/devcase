package com.vsm.devcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.devcase.model.UsuarioPermissao;

public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissao, Long> {
	
	UsuarioPermissao findByDescricao(String descricao);

}
