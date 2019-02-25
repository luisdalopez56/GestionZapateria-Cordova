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
import javax.persistence.Table;

@Entity
@Table(name = "producto_idioma")
@NamedQueries({
    @NamedQuery(name = "ProductoIdioma.findAll", 
    		query = "SELECT pi FROM ProductoIdioma pi")
    , @NamedQuery(name = "ProductoIdioma.findById", 
    		query = "SELECT pi FROM ProductoIdioma pi WHERE pi.id = :id"),
    @NamedQuery(name = "ProductoIdioma.findByIdProducto", 
	query = "SELECT pi FROM ProductoIdioma pi WHERE pi.id_producto = :id_producto")})
public class ProductoIdioma implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 2)
	private String cod_idioma;
    @Basic(optional = false)
    @Column(nullable = false, length = 40)
	private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
	private String descripcion;
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id_producto;
	
    public ProductoIdioma() {}

	public ProductoIdioma(Long id, String cod_idioma, String nombre, String descripcion, Long id_producto) {
		super();
		this.id = id;
		this.cod_idioma = cod_idioma;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.id_producto = id_producto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCod_idioma() {
		return cod_idioma;
	}

	public void setCod_idioma(String cod_idioma) {
		this.cod_idioma = cod_idioma;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId_producto() {
		return id_producto;
	}

	public void setId_producto(Long id_producto) {
		this.id_producto = id_producto;
	};
	
	
    
    
	
}
