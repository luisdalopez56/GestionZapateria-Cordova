package com.iesvdc.acceso.zapateria.gestionzapateria;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class RepositorioClientesImpl implements RepositorioClientesCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Cliente> findByNombre(String nombre) {        
          Query query = entityManager.createNativeQuery("SELECT * FROM zapateria.cliente " +
                "WHERE nombre LIKE ?", Cliente.class);
    		query.setParameter(1, nombre + "%");

        return query.getResultList();
    }

}
