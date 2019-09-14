package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Signos;
import com.mitocode.service.ISignosService;

@RestController
@RequestMapping("/signos")
public class SignosController {

	@Autowired
	private ISignosService service;

	@GetMapping
	public ResponseEntity<List<Signos>> listar() {
		List<Signos> signos = service.obtenerSignosVitales();
		return new ResponseEntity<List<Signos>>(signos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Signos> leerPorId(@PathVariable("id") Integer id) {
		Signos obj = service.obtenerSignoVital(id);
		if(obj==null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
		}
		return new ResponseEntity<Signos>(obj, HttpStatus.OK);
	}
}
