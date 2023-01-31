package org.generation.ecommercedb.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;

import org.generation.ecommercedb.config.JwtFilter;
import org.generation.ecommercedb.model.Token;
import org.generation.ecommercedb.model.Usuario;
import org.generation.ecommercedb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(path="/api/login/")
public class LoginController {
	// CRUD???
	
	private final UsuarioService usuarioService;
	@Autowired
	public LoginController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}//constructor

	@PostMapping
	public Token login (@RequestBody Usuario usuario) throws ServletException {
		if (usuarioService.validaUsuario(usuario)) {
			return new Token(generateToken(usuario.getEmail()));
		}//if
		throw new ServletException("Nombre de usuario o contraseña incorrectos");
	}//login
	
	private String generateToken(String username) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 10);
		return Jwts.builder().setSubject(username).claim("role", "user")
				.setIssuedAt(new Date()).setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS256, JwtFilter.secret).compact();
	}// generateToken
}//class LoginController
