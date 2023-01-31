package org.generation.ecommercedb.service;
import java.util.List;
import java.util.Optional;

import org.generation.ecommercedb.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductoService {
	private final ProductoRepository productoRepository;
	@Autowired
	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}//constructor
	public List<Producto> getAllProductos(){
		return productoRepository.findAll();
	}//getAllProductos
	
	public Producto getProducto(Long id){
		return productoRepository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("El Producto con el id " + id +
						" no existe.")
				);
	}//getProducto
	public Producto deleteProducto(Long id) {
		Producto tmp=null;
		if (productoRepository.existsById(id)) {
			tmp=productoRepository.findById(id).get();
			productoRepository.deleteById(id);
		}//if
		return tmp;
	}//deleteProducto
	
	public Producto addProducto(Producto producto) {
		return productoRepository.save(producto);
	}//addProducto
	public Producto updateProducto(Long id, String nombre, String descripcion, 
			String imagen, Double precio) {
		Producto tmp=null;
		if (productoRepository.existsById(id)) {
			tmp=productoRepository.findById(id).get();
			if (nombre!=null) tmp.setNombre(nombre);
			if (descripcion!=null) tmp.setDescripcion(descripcion);
			if (imagen!=null) tmp.setImagen(imagen);
			if (precio!=null) tmp.setPrecio(precio.doubleValue());
			productoRepository.save(tmp);
		} else {
			System.out.println("Update - El Producto con el id " + id + 
					" no existe");
		}//if
		return tmp;
	}//updateProducto
}//class ProductoService
