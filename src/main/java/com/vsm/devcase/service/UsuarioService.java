package com.vsm.devcase.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.config.MeuPasswordEncoder;
import com.vsm.devcase.model.Usuario;
import com.vsm.devcase.model.UsuarioPermissao;
import com.vsm.devcase.repository.UsuarioPermissaoRepository;
import com.vsm.devcase.repository.UsuarioRepository;
import com.vsm.devcase.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioPermissaoRepository permissaoRepository;
	
	public List<Usuario> listarTodosUsuarios() {
		return repository.findAll();
	}
	
	public Usuario salvarNovoUsuario(Usuario usuario) {
		MeuPasswordEncoder encoder = new MeuPasswordEncoder();
		String senhaEncode = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncode);
		UsuarioPermissao permissaoPadrao = permissaoRepository.findByDescricao("comum");
		usuario.setPermissao(Arrays.asList(permissaoPadrao));
		return repository.save(usuario);
	}
	
	public void excluirUsuario(Long id) {
		Optional<Usuario> cidade = buscarUsuarioPorId(id);
		repository.deleteById(cidade.get().getId());
	}
	
	public Optional<Usuario> buscarUsuarioPorId(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrada!"));
		return usuario;
	}
	
	public Usuario atualizarUsuario(Long id, Usuario usuario) {
		Usuario usuarioSalvo = repository.getOne(id);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		return repository.save(usuarioSalvo);
	}
	
}
