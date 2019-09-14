package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mitocode.model.Signos;

public interface ISignosRepo extends JpaRepository<Signos, Integer> {

	@Query("SELECT s FROM Signos s ORDER BY s.fecha DESC")
	public List<Signos> obtenerSignosVitales();
}
