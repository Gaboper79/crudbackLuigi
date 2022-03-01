package com.tuto.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuto.crud.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	Optional<Producto> findByName(String nombre);

	boolean existsByName(String nombre);
}
