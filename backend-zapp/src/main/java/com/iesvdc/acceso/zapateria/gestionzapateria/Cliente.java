/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.zapateria.gestionzapateria;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author profesor
 */
@Entity
@NamedQueries({
    @NamedQuery(
    		name = "Cliente.findAll", 
    		query = "SELECT c FROM Cliente c")
    , @NamedQuery(
    		name = "Cliente.findById", 
    		query = "SELECT c FROM Cliente c WHERE c.id = :id")
    , @NamedQuery(
    		name = "Cliente.findByNombre", 
    		query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(
    		name = "Cliente.findByApellidos", 
    		query = "SELECT c FROM Cliente c WHERE c.apellidos = :apellidos")
    , @NamedQuery(
    		name = "Cliente.findByDni", 
    		query = "SELECT c FROM Cliente c WHERE c.dni = :dni")})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 40)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String apellidos;
    @Basic(optional = false)
    @Column(nullable = false)
    private int dni;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    //@JsonManagedReference
    private List<ClienteDireccion> clienteDireccionList = new ArrayList<ClienteDireccion>();

    public Cliente() {
    }

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(Long id, String nombre, String apellidos, int dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public List<ClienteDireccion> getClienteDireccionList() {
        return clienteDireccionList;
    }

    public void setClienteDireccionList(List<ClienteDireccion> clienteDireccionList) {
        this.clienteDireccionList = clienteDireccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cliente[ id=" + id + " ]";
    }

}
