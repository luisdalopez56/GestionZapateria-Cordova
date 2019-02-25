/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvdc.acceso.zapateria.gestionzapateria;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author profesor
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class Controlador {
    // Repositorios
	
    @Autowired
    RepositorioClientes repoClient;
    @Autowired
    RepositorioCodPos repoCodPos;
    @Autowired
    RepositorioClienteDireccion repoClienteDireccion;
    
    @Autowired
    RepositorioProductoCategorias repoProductoCategorias;
    
    @Autowired
    RepositorioProductos repoProduct;
    
    @Autowired
    RepositorioProductoIdiomas repoProductIdiomas;
    
    
    
    // Get All Clientes
    @GetMapping("/cliente")
    public List<Cliente> getAllAlumnos() {
        return repoClient.findAll();
    }
    
    @GetMapping("/cliente/nombre/{nombre}")
    public List<Cliente> getClienteByNombre(@PathVariable(value = "nombre") String nombre) {
        return repoClient.findByNombre(nombre);
    }
    @GetMapping("/cliente/apellidos/{apellidos}")
    public List<Cliente> getClienteByApellidos(@PathVariable(value = "apellidos") String apellidos) {
        return repoClient.findByApellidos(apellidos);
    }
    
    @GetMapping("/cliente/dni/{dni}")
    public List<Cliente> getClienteByDni(@PathVariable(value = "dni") int dni) {
        return repoClient.findByDni(dni);
    }
    
    // Get a Single Client
    @GetMapping("/cliente/{id}")
    public Cliente getCliente(@PathVariable(value = "id") Long clienteId) {
        return repoClient.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
    }
    
    
    @PostMapping(value="/cliente", consumes={"application/json"})
    @ResponseBody public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
        System.out.println("\n\n\nINTENTANDO GUARDAR CLIENTE DNI="+cliente.getDni());
        return repoClient.save(cliente);
    }
    
    
    // UPDATE CLIENTE
    @PutMapping(value="/cliente/{id}", consumes={"application/json"})
    @ResponseBody public Cliente updateCliente(
    			@Valid @RequestBody Cliente cliente,
    			@PathVariable(value = "id") Long clienteId) {
        // System.out.println("\n\n\nINTENTANDO ACTUALIZAR CLIENTE DNI="+cliente.getDni());
        Cliente new_cli = repoClient.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
        new_cli.setApellidos(cliente.getApellidos());
        new_cli.setNombre(cliente.getNombre());
        new_cli.setDni(cliente.getDni());
        //new_cli.setClienteDireccionList(cliente.getClienteDireccionList());
        return repoClient.save(new_cli);
    }
    // DELETE CLIENTE
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable(value = "id") Long clienteId) {
        Cliente cliente = repoClient.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
        repoClient.delete(cliente);
        return ResponseEntity.ok().build();
    }
    // Get All CodPos
    @GetMapping("/codpos")
    public List<CodPos> getAllCodPos() {
        return repoCodPos.findAll();
    }
    
    @PostMapping(value="/codpos", consumes={"application/json"})
    @ResponseBody public CodPos createCodPos(@Valid @RequestBody CodPos codpos) {
        System.out.println("\n\n\nINTENTANDO GUARDAR CODPOS CP="+codpos.getCp());
        return repoCodPos.save(codpos);
    }
/*
    @DeleteMapping("/codpos/{cp}")
    public ResponseEntity<?> deleteCodPos(@PathVariable(value = "cp") Long cp) {
        CodPos codpos = repoCodPos.findById(cp)
                .orElseThrow(() -> new ResourceNotFoundException("CodPos", "cp", cp));
        repoCodPos.delete(codpos);
        return ResponseEntity.ok().build();
    }
*/
    
    /*
    // Get All ClienteDireccion
    @GetMapping("/cliente/direccion")
    public List<ClienteDireccion> getAllClienteDireccion() {
        return repoClienteDireccion.findAll();
    }*/
    
    // Get All ClienteDireccion
    @GetMapping("/cliente/{idCli}/direccion")
    public List<ClienteDireccion> getClienteDireccionByCliente(@PathVariable(value = "idCli") Long idCli) {
    	Cliente cliente = repoClient.findById(idCli).get();
        return cliente.getClienteDireccionList();
    }
    
    @PostMapping(
    			value="/cliente/{idCli}/direccion", 
    			consumes={"application/json"})
    @ResponseBody List<ClienteDireccion> postClienteDireccion(
    		@PathVariable(value = "idCli") Long idCli,
    		@Valid @RequestBody ClienteDireccion cli_dir){
    	Cliente cli = repoClient.findById(idCli).orElseThrow(() 
    			-> new ResourceNotFoundException("Cliente", "id", idCli));
    	cli_dir.setIdCliente(cli);
    	repoClienteDireccion.save(cli_dir);
    	return cli.getClienteDireccionList();
    }
    
    @DeleteMapping(
			value="/cliente/{idCli}/direccion/{idDirCli}")
    @ResponseBody List<ClienteDireccion> deleteClienteDireccion(
	@PathVariable(value = "idCli") Long idCli,
	@PathVariable(value = "idDirCli") Long idDirCli){
    		
	ClienteDireccion cli_dir = repoClienteDireccion.findById(idDirCli).orElseThrow(() 
			-> new ResourceNotFoundException("ClienteDireccion", "idDirCli", idDirCli));;
	
	repoClienteDireccion.delete(cli_dir);
	
	Cliente cli = repoClient.findById(idCli).orElseThrow(() 
			-> new ResourceNotFoundException("Cliente", "id", idCli));
	
	return cli.getClienteDireccionList();
}
    
    /*
    @DeleteMapping("/codpos/{cp}")
    public ResponseEntity<?> deleteCodPos2(@PathVariable(value = "cp") Long cp) {
        CodPos codpos = repoCodPos.findById(cp)
                .orElseThrow(() -> new ResourceNotFoundException("CodPos", "cp", cp));
        repoCodPos.delete(codpos);
        return ResponseEntity.ok().build();
    }
	*/
    /*
    // Get All Notes
    @GetMapping("/cliente/{id}")
    public List<ClienteDireccion> getDireccions(Long id) {
        return repoClient.();
    }
     */
    
  //PRODUCTO
    @GetMapping("/producto")
    public List<Producto> getAllProducto(){
    	return repoProduct.findAll();
    }
    
    @GetMapping("/producto/{id}")
    public Producto getProducto(@PathVariable(value = "id") Long categoriaId) {
        return repoProduct.findById(categoriaId).
        		orElseThrow(() -> new ResourceNotFoundException("Producto", "id", categoriaId)); 
    }
    
    /*@GetMapping("/producto/{id_producto}/productoidioma")
    public List<ProductoIdioma> getIdiomas(@PathVariable(value= "id_producto") Long idProducto){
    	
    	return repoProductIdiomas.findByIdProducto(idProducto);
    }*/
    
    
    //PRODUCTO IDIOMA
    @GetMapping("/productoidioma")
    public List<ProductoIdioma> getAllProductoIdioma(){
    	System.out.println("En PRODUCTO IDIOMAS BUSCANDO");
    	return repoProductIdiomas.findAll();
    }
    
    @GetMapping("/productoidioma/{id}")
    public ProductoIdioma getProductoIdioma(@PathVariable(value = "id") Long productoIdiomaId) {
        return repoProductIdiomas.findById(productoIdiomaId).
        		orElseThrow(() -> new ResourceNotFoundException("ProductoIdioma", "id", productoIdiomaId)); 
    }
    
    @GetMapping("/productoidioma/producto/{id_producto}")
    public List<ProductoIdioma> getProductoIdiomaProductoID(@PathVariable(value = "id_producto") Long id_producto) {
        return repoProductIdiomas.findByIdProducto(id_producto);
    }
    
    //PRODUCTO CATEGORIA
    @GetMapping("/productocategoria")
    public List<ProductoCategoria> getAllProductoCategoria(){
    	return repoProductoCategorias.findAll();
    }
    
    @GetMapping("/productocategoria/{id}")
    public ProductoCategoria getProductoCategoria(@PathVariable(value = "id") Long productoCategoriaId) {
        return repoProductoCategorias.findById(productoCategoriaId).
        		orElseThrow(() -> new ResourceNotFoundException("ProductoCategoria", "id", productoCategoriaId)); 
    }
    
    @PostMapping(value="/productocategoria", consumes={"application/json"})
    @ResponseBody public ProductoCategoria createCategoria(@Valid @RequestBody ProductoCategoria productoCategoria) {
        System.out.println("\n\n\nINTENTANDO GUARDAR Categoria");
        return repoProductoCategorias.save(productoCategoria);
    }
    
    @DeleteMapping("/productocategoria/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable(value = "id") Long categoriaId) {
        ProductoCategoria categoria = repoProductoCategorias.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductoCategoria", "id", categoriaId));
        repoProductoCategorias.delete(categoria);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(value="/productocategoria/{id}", consumes={"application/json"})
    @ResponseBody public ProductoCategoria updateCategoria(
    			@Valid @RequestBody ProductoCategoria categoria,
    			@PathVariable(value = "id") Long categoriaId) {
        ProductoCategoria new_cat = repoProductoCategorias.findById(categoriaId).orElseThrow(() -> new ResourceNotFoundException("ProductoCategoria", "id", categoriaId));
        new_cat.setNombre(categoria.getNombre());
        //new_cli.setClienteDireccionList(cliente.getClienteDireccionList());
        return repoProductoCategorias.save(new_cat);
    }
    
 // Get products of categorias
    @GetMapping("/productocategoria/{idCat}/producto")
    public List<Producto> getProductoByProductoCategoria(@PathVariable(value = "idCat") Long idCat) {
    	ProductoCategoria productoCategoria = repoProductoCategorias.findById(idCat).get();
        return productoCategoria.getProductoCategoriaList();
    }
}
    
    
    
    
    
    
