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

import com.vsm.devcase.model.Usuario;
import com.vsm.devcase.service.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = service.listarTodosUsuarios();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Usuario> novaUsuario(@Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = service.salvarNovoUsuario(usuario);
		return ResponseEntity.ok().body(usuarioSalvo);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
		service.excluirUsuario(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
		Optional<Usuario> usuario = service.buscarUsuarioPorId(id);
		return ResponseEntity.of(usuario);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioAtualizado = service.atualizarUsuario(id, usuario);
		return ResponseEntity.ok().body(usuarioAtualizado);
	}
	
}
