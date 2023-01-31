package org.generation.ecommercedb.controller;

import java.util.List;

import org.generation.ecommercedb.model.Producto;
import org.generation.ecommercedb.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/productos/")
//@CrossOrigin(origins="http://localhost:5500", methods= {RequestMethod.GET, RequestMethod.POST})
public class ProductoController {
	private final ProductoService productoService;
	
	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}//constructor
	
	@GetMapping      // http://localhost:8080/api/productos/
	public List<Producto> getAllProductos(){
		return productoService.getAllProductos();
	}//getAllProductos
	
	@GetMapping(path="{prodId}") // http://localhost:8080/api/productos/1
	public Producto getProducto(@PathVariable("prodId") Long id) {
		return productoService.getProducto(id);
	}//getProducto
	
	@DeleteMapping(path="{prodId}") // http://localhost:8080/api/productos/1
	public Producto deleteProducto(@PathVariable("prodId") Long id) {
		return productoService.deleteProducto(id);
	}//deleteProducto
	
	@PostMapping 
	public Producto addProducto(@RequestBody Producto producto) {
		return productoService.addProducto(producto);
	}//addProducto
	
	@PutMapping (path="{prodId}") // http://localhost:8080/api/productos/1
	public Producto updateProducto(@PathVariable("prodId") Long id, 
		@RequestParam(required = false)	String nombre,
		@RequestParam(required = false)	String descripcion,
		@RequestParam(required = false) String imagen,
		@RequestParam(required = false) Double precio) {
		return productoService.updateProducto(id, nombre, 
								descripcion, imagen, precio);
	}//updateProducto
	
	
	
	
	
	
}//class ProductoController
