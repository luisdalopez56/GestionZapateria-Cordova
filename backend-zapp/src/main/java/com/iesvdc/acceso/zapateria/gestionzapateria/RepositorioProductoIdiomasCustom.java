package com.iesvdc.acceso.zapateria.gestionzapateria;

import java.util.List;

public interface RepositorioProductoIdiomasCustom {

	List<ProductoIdioma> findByIdProducto(Long id_producto);

}
