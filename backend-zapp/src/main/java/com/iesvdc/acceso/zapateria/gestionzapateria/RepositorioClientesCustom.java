/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.zapateria.gestionzapateria;

import java.util.List;


/**
 *
 * @author profesor
 */
public interface RepositorioClientesCustom  {

	List<Cliente> findByNombre(String nombre);

}
