package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Signos;

public interface ISignosService extends ICRUD<Signos> {

	List<Signos> obtenerSignosVitales();

}
