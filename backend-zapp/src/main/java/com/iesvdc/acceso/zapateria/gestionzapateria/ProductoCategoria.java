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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "producto_categoria")
@NamedQueries({
    @NamedQuery(name = "ProductoCategoria.findAll", 
    		query = "SELECT pc FROM ProductoCategoria pc")
    , @NamedQuery(name = "ProductoCategoria.findById", 
    		query = "SELECT pc FROM ProductoCategoria pc WHERE pc.id = :id")
    , @NamedQuery(name = "ProductoCategoria.findByNombre", 
    		query = "SELECT pc FROM ProductoCategoria pc WHERE pc.nombre = :nombre")})
public class ProductoCategoria implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 40)
    private String nombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private List<Producto> productoCategoriaList = new ArrayList<Producto>();
    
    public ProductoCategoria() {
    	
    }
    
	public ProductoCategoria(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
	public List<Producto> getProductoCategoriaList() {
		return productoCategoriaList;
	}
	public void setProductoCategoriaList(List<Producto> productoCategoriaList) {
		this.productoCategoriaList = productoCategoriaList;
	}
    
    
    
}

























