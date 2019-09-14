package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Signos;
import com.mitocode.repo.ISignosRepo;
import com.mitocode.service.ISignosService;

@Service
public class SignosServiceImpl implements ISignosService {

	@Autowired
	ISignosRepo repo;

	@Override
	public List<Signos> obtenerSignosVitales() {
		return repo.obtenerSignosVitales();
	}
	
	@Override
	public Signos obtenerSignoVital(Integer id) {
		return repo.findOne(id);
	}

}
