package com.tuto.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuto.crud.dto.Mensaje;
import com.tuto.crud.dto.ProductoDTO;
import com.tuto.crud.model.Producto;
import com.tuto.crud.service.ProductoService;

@RestController
@RequestMapping("/producto")
@CrossOrigin
public class ProductoController {

	@Autowired
	ProductoService productoSvc;

	@GetMapping("/lista")
	public ResponseEntity<List<Producto>> getAllProductos() {

		return new ResponseEntity<List<Producto>>(productoSvc.list(), HttpStatus.OK);
	}

	@GetMapping("/detalle/{id}")
	public ResponseEntity<Producto> getById(@PathVariable("id") int id) {
		if (!productoSvc.existsById(id))
			return new ResponseEntity(new Mensaje("No existe producto con es id!"), HttpStatus.NOT_FOUND);

		Producto producto = productoSvc.getProducto(id).get();
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);

	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ProductoDTO productoDto) {

		if (org.apache.commons.lang3.StringUtils.isAllBlank(productoDto.getName())) {
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if (productoDto.getPrice() < 0) {
			return new ResponseEntity(new Mensaje("El precio debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
		}
		Producto newProducto = new Producto(productoDto.getName(), productoDto.getPrice());
		productoSvc.save(newProducto);

		return new ResponseEntity(new Mensaje("Producto creado "), HttpStatus.CREATED);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProductoDTO productoDto) {

		System.out.println(productoDto.getName());
		if (!productoSvc.existsById(id))
			return new ResponseEntity(new Mensaje("No existe producto con es id!"), HttpStatus.NOT_FOUND);
		if (org.apache.commons.lang3.StringUtils.isAllBlank(productoDto.getName())) {
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if (productoDto.getPrice() < 0) {
			return new ResponseEntity(new Mensaje("El precio debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
		}

		Producto newProducto = new Producto(productoDto.getName(), productoDto.getPrice());
		newProducto.setId(id);
		productoSvc.save(newProducto);

		return new ResponseEntity(new Mensaje("Producto Actualizado "), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!productoSvc.existsById(id))
			return new ResponseEntity(new Mensaje("No existe producto con es id!"), HttpStatus.NOT_FOUND);

		productoSvc.delete(id);
		return new ResponseEntity(new Mensaje("Producto Borrado! "), HttpStatus.OK);
	}
}
