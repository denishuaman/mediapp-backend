package com.mitocode.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.ResetToken;
import com.mitocode.model.Usuario;
import com.mitocode.service.ILoginService;
import com.mitocode.service.IResetTokenService;
import com.mitocode.util.EmailService;
import com.mitocode.util.Mail;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ILoginService service;
	
	@Autowired
	private IResetTokenService tokenService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@PostMapping(value = "/enviarCorreo", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Integer> enviarCorreo(@RequestBody String correo) {
		int rpta = 0;
		try {
			Usuario us = service.verificarNombreUsuario(correo);
			if (us != null && us.getIdUsuario() > 0) {
			
				ResetToken token = new ResetToken();
				token.setToken(UUID.randomUUID().toString());
				token.setUsuario(us);
				token.setExpiracion(10);
				tokenService.guardar(token);
				
				Mail mail = new Mail();
				mail.setFrom("email.prueba.demo@gmail.com");
				mail.setTo(us.getUsername());
				mail.setSubject("RESTABLECER CONTRASEÑA - MEDIAPP");
				
				Map<String, Object> model = new HashMap<>();
				String url = "http://localhost:4200/#/recuperar/" + token.getToken();
				model.put("user", token.getUsuario().getUsername());
				model.put("resetUrl", url);
				mail.setModel(model);
				emailService.sendEmail(mail);
				rpta = 1;
			}
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
		
	@GetMapping(value = "/restablecer/verificar/{token}")
	public ResponseEntity<Integer> restablecerClave(@PathVariable("token") String token) {
		int rpta = 0;
		try {
			if (token != null && !token.isEmpty()) {
				ResetToken rt = tokenService.findByToken(token);
				if (rt != null && rt.getId() > 0) {
					if (!rt.isExpirado()) {
						rpta = 1;
					}
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@PostMapping(value = "/restablecer/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> restablecerClave(@PathVariable("token") String token, @RequestBody String clave) {
		int rpta = 0;
		try {
			ResetToken rt = tokenService.findByToken(token);
			String claveHash = bcrypt.encode(clave);
			rpta = service.cambiarClave(claveHash, rt.getUsuario().getUsername());
			tokenService.eliminar(rt);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}

}
