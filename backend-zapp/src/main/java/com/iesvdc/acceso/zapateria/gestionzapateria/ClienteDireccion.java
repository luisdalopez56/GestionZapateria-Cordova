/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.zapateria.gestionzapateria;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
// import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author profesor
 */
@Entity
@Table(name = "cliente_direccion")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idDireccion")
public class ClienteDireccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(length = 100)
    private String nombre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_direccion", nullable = false)
    private Long idDireccion;
    @Basic(optional = false)
    @Column(name = "nombre_via", nullable = false, length = 150)
    private String nombreVia;

    @JoinColumn(name = "cp", referencedColumnName = "cp", nullable = false)
    @ManyToOne(optional = false)
    //  @JsonManagedReference
    private CodPos cp;

    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private Cliente idCliente;

    public ClienteDireccion() {
    }

    public ClienteDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public ClienteDireccion(Long idDireccion, String nombreVia) {
        this.idDireccion = idDireccion;
        this.nombreVia = nombreVia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public CodPos getCp() {
        return cp;
    }

    public void setCp(CodPos cp) {
        this.cp = cp;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDireccion != null ? idDireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteDireccion)) {
            return false;
        }
        ClienteDireccion other = (ClienteDireccion) object;
        if ((this.idDireccion == null && other.idDireccion != null) || (this.idDireccion != null && !this.idDireccion.equals(other.idDireccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ClienteDireccion[ idDireccion=" + idDireccion + " ]";
    }

}
