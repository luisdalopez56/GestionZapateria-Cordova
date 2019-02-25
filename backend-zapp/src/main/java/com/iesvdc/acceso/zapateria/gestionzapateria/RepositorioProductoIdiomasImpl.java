package com.iesvdc.acceso.zapateria.gestionzapateria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class RepositorioProductoIdiomasImpl implements RepositorioProductoIdiomasCustom {

	@PersistenceContext
    EntityManager entityManager;
	
	@Override
	public List<ProductoIdioma> findByIdProducto(Long id_producto) {
		Query query = entityManager.createNativeQuery("SELECT * FROM zapateria.producto_idioma " +
                "WHERE id_producto LIKE ?", ProductoIdioma.class);
    		query.setParameter(1, id_producto + "%");

        return query.getResultList();
	}

	

}

