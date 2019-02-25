package com.iesvdc.acceso.zapateria.gestionzapateria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProductoIdiomas extends JpaRepository<ProductoIdioma, Long> {

	@Query
	List<ProductoIdioma> findByIdProducto(Long id_producto);

	
}
