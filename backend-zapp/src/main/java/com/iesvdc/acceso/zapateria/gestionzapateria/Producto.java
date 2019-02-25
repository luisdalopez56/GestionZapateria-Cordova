package com.iesvdc.acceso.zapateria.gestionzapateria;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 40)
    private String rutaImagen;
    
    @JoinColumn(name = "categoria", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private ProductoCategoria categoria;
    public Producto() {
    	
    }
    
	public Producto(Long id, String rutaImagen, ProductoCategoria categoria) {
		super();
		this.id = id;
		this.rutaImagen = rutaImagen;
		this.categoria = categoria;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public ProductoCategoria getCategoria() {
		return categoria;
	}
	public void setCategoria(ProductoCategoria categoria) {
		this.categoria = categoria;
	}
    
    
    
    
}
