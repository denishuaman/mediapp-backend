package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Signos;

public interface ISignosService {

	List<Signos> obtenerSignosVitales();

	Signos obtenerSignoVital(Integer id);
}
