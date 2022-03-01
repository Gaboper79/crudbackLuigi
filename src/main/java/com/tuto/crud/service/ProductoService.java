package com.tuto.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuto.crud.model.Producto;
import com.tuto.crud.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {

	@Autowired
	ProductoRepository productoRepo;

	public List<Producto> list() {
		return productoRepo.findAll();
	}

	public Optional<Producto> getProducto(int id) {

		return productoRepo.findById(id);
	}

	public Optional<Producto> getByName(String name) {
		return productoRepo.findByName(name);
	}

	public void save(Producto producto) {
		productoRepo.save(producto);
	}

	public void delete(int id) {
		productoRepo.deleteById(id);
	}

	public boolean existsById(int id) {
		return productoRepo.existsById(id);
	}

	public boolean existsByName(String name) {
		return productoRepo.existsByName(name);
	}
}
