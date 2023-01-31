package org.generation.ecommercedb.service;
import org.generation.ecommercedb.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}//interface ProductoRepository