package com.iesvdc.acceso.zapateria.gestionzapateria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProductos extends JpaRepository<Producto, Long> {
 
}
