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
	public Signos registrar(Signos signos) {
		return repo.save(signos);
	}

	@Override
	public Signos modificar(Signos signos) {
		return repo.save(signos);
	}

	@Override
	public Signos leerPorId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public List<Signos> listar() {
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}
}
