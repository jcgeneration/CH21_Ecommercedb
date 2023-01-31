package org.generation.ecommercedb.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecommercedb.model.Producto;
import org.generation.ecommercedb.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}//constructor
	
	public List<Usuario> getAllUsuarios(){
		return usuarioRepository.findAll();
	}//getAllUsuario
	
	public Usuario getUsuario(Long id){
		return usuarioRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El Usuario con el id " +id +
                        " no existe.")
        );
	}//getUsuario
	
	public Usuario deleteUsuario(Long id) {
		Usuario tmp = null;
		if (usuarioRepository.existsById(id)) {
			tmp = usuarioRepository.findById(id).get();
			usuarioRepository.deleteById(id);
		} else {
			System.out.println("El Usuario con el id " +id +" no existe.");
		}//else 
		return tmp;
	}//deleteUsuario
	
	public Usuario addUsuario(Usuario usuario){
		System.out.println(usuario);
		usuario.setPassword( passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}//addUsuario
	
	public Usuario updateUsuario(Long id, String password, String newPassword) {
		Usuario tmp=null;
		if (usuarioRepository.existsById(id)) {
			if ((password!=null) &&  (newPassword!=null)) {
				tmp=usuarioRepository.findById(id).get();
				if (passwordEncoder.matches(password, tmp.getPassword())) {
				//if (password.equals(tmp.getPassword())) {
					tmp.setPassword(passwordEncoder.encode(newPassword));
					usuarioRepository.save(tmp);
				} else {
					tmp=null;
				}// if password	
			} //if !=null;
			usuarioRepository.save(tmp);
		} else {
			System.out.println("Update - El Usuario con id " + id + 
					" no existe");
		}//if
		return tmp;
	}//updateProducto
 public boolean validaUsuario(Usuario usuario) {
		Optional<Usuario> userByEmail= usuarioRepository.findByEmail(usuario.getEmail());
		if (userByEmail.isPresent()) {
			Usuario u = userByEmail.get();
			if (passwordEncoder.matches(usuario.getPassword(), u.getPassword())) {
			//if (u.getPassword().equals(usuario.getPassword())) {
				return true;
			}//if equals
		}//if isPresent
		  return false;
	}//validaUsuario
	
	
	
}//class UsuarioService
