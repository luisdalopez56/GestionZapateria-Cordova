/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.zapateria.gestionzapateria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author profesor
 */
@Repository
public interface RepositorioClientes extends JpaRepository<Cliente, Long> {

	// Con un repo custom
	List<Cliente> findByNombre(String nombre);
	// Con namedQueries
	List<Cliente> findByApellidos(@Param("apellidos") String apellidos);
	List<Cliente> findByDni(@Param("dni") int dni);
 
}
