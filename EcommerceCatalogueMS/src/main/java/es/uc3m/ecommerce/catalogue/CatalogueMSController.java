package es.uc3m.ecommerce.catalogue;

import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.ecommerce.catalogue.model.Product;
import es.uc3m.ecommerce.catalogue.model.ProductDAO;
import es.uc3m.ecommerce.catalogue.model.Category;
import es.uc3m.ecommerce.catalogue.model.CategoryDAO;

@CrossOrigin
@RestController
@Controller
public class CatalogueMSController {

	@Autowired
	CategoryDAO categoryDAO;
	

	@Autowired
	ProductDAO productDAO;
	
	//PRODUCT - LLAMADAS GET
	// buscar producto por su ID
	@RequestMapping(value="/products/{id}", method= RequestMethod.GET) 
	public ResponseEntity<Product> findProductById(@PathVariable int id){
		
		ResponseEntity<Product> response = null;
		Product p = productDAO.findById(id).orElse(null);
		
		if (p == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(p, HttpStatus.OK);
		}
		return response;
	}
	
	// buscar todos los productos
	@RequestMapping(value="/products", method= RequestMethod.GET) 
	public ResponseEntity<List<Product>> findAllProducts(){
		
		ResponseEntity<List<Product>> response = null;
		List<Product> pList = (List<Product>) productDAO.findAll();
		
		if (pList == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(pList, HttpStatus.OK);
		}
		return response;
	}
	
	// Buscar producto según sus atributos descripcion, precio, nombre y categoria
	@RequestMapping(value="/products/attributes", method= RequestMethod.GET) // buscar todos los productos filtrados por campos
	public ResponseEntity<List<Product>> findByAttributes(
			@RequestParam(value="productName", required = false) String productName,
			@RequestParam(value="shortDesc", required = false) String shortDesc,
			@RequestParam(value="category", required = false) Integer categoryId, 
			@RequestParam(value="priceMin", required = false) Integer priceMin,
			@RequestParam(value="priceMax", required = false) Integer priceMax){
		
		ResponseEntity<List<Product>> response = null;
		List<Product> pList = null; 
		
		if(priceMin == null) {
			priceMin = 0; 
		}
		if(priceMax == null) {
			priceMax = Integer.MAX_VALUE;
		}
		
		// Sin filtro de busqueda mostrar todos los productos con precio de 0 a Integer.MAX_VALUE
		
		if(productName == null && shortDesc == null && categoryId == null) {
			pList = (List<Product>) productDAO.findByPrice(priceMin, priceMax);		
		}
		/* BUSQUEDA POR 1 ATRIBUTO + rango de precios */
		// Buscar solo por nombre de producto
		else if(productName != null && shortDesc == null && categoryId == null) {
			pList = (List<Product>) productDAO.findByNameAndPrice(productName, priceMin, priceMax);		
		}
		// Buscar solo por descripcion
		else if(productName == null && shortDesc != null && categoryId == null) {
			pList = (List<Product>) productDAO.findByDescAndPrice(shortDesc, priceMin, priceMax);		
		}
		// Buscar solo por cateogria
		else if(productName == null && shortDesc == null && categoryId != null) {
			pList = (List<Product>) productDAO.findByCategoryAndPrice(categoryId, priceMin, priceMax);		
		}		
		
		/* BUSQUEDA POR 2 ATRIBUTOS + rango de precios */
		// Buscar por nombre y descripcion
		else if(productName != null && shortDesc != null && categoryId == null) {
			pList = (List<Product>) productDAO.findByPriceAndNameAndDesc(priceMin, priceMax, productName, shortDesc);		
		}
		// Buscar por nombe y categoria
		else if(productName != null && shortDesc == null && categoryId != null) {
			pList = (List<Product>) productDAO.findByNameAndCategoryAndPrice(productName, categoryId, priceMin, priceMax);		
		}
		// Buscar por descripcion y categoria
		else if(productName == null && shortDesc != null && categoryId != null) {
			pList = (List<Product>) productDAO.findByPriceAndCategoryAndDesc(priceMin, priceMax, categoryId, shortDesc);		
		}
		/* BUSQUEDA POR 3 ATRIBUTOS + rango de precios */
		
		// Buscar por nombre, categoria y descripcion
		else {
			pList = (List<Product>) productDAO.findByPriceAndNameAndDescAndCategory(priceMin, priceMax, productName, categoryId, shortDesc);		
		}
		
		// COMPROBAR SI LA BUSQUEDA A RETORNADO RESULTADOS
		if (pList == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(pList, HttpStatus.OK);
		}
		return response;
	}
	
	// PRODUCT - LLAMADAS POST
	// insertar un nuevo producto
	@RequestMapping(value="/products", method= RequestMethod.POST) 
	public ResponseEntity<Product> insertProduct(@RequestBody @Validated Product p){
		
		ResponseEntity<Product> response = null;
		productDAO.save(p);
		
		if (p == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(p, HttpStatus.CREATED);
		}
		return response;
	}
	
	// PRODUCT - LLAMADAS PUT
	// Editar un producto existente por si ID, dado otro objeto con los nuevos atributos deseados
	@RequestMapping(value="/products/{id}", method= RequestMethod.PUT) 
	public ResponseEntity<Product> modifyProductById(@PathVariable int id, @RequestBody @Validated Product newP){
		
		ResponseEntity<Product> response = null;
		Product p = productDAO.findById(id).orElse(null);
		
		if (p == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			
			// Edit product with new attributes
			p.setPrice(newP.getPrice());
			p.setAppuser(newP.getAppuser());
			p.setCategoryBean(newP.getCategoryBean());
			p.setLongDesc(newP.getLongDesc());
			p.setProductId(newP.getProductId());
			p.setProductName(newP.getProductName());
			p.setProductPicture(newP.getProductPicture());
			p.setPurchases(newP.getPurchases());
			p.setShortDesc(newP.getShortDesc());
			p.setStock(newP.getStock());
			
			productDAO.save(p);

			response = new ResponseEntity<>(p, HttpStatus.OK);
		}
		return response;
	}
	
	// PRODUCT - LLAMADAS DELETE
	// Borrar producto por su ID
	@RequestMapping(value="/products/{id}", method= RequestMethod.DELETE) // eliminar un producto (editar el flag isDeleted)
	public ResponseEntity<Product> deleteProduct(@PathVariable int id){
		
		ResponseEntity<Product> response = null;
		Product p = productDAO.findById(id).orElse(null);
		
		if (p == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			
			p.setIsDeleted(1);
			
			productDAO.save(p);
			
			response = new ResponseEntity<>(p, HttpStatus.OK);
		}
		return response;
	}
	
	

	/*Buscar categoría por id*/

	@RequestMapping(value="/categories/{id}", method= RequestMethod.GET)
	public ResponseEntity<Category> getCategoryById(@PathVariable int id){
		System.out.println("looking for id");
		ResponseEntity<Category> response = null;
		Category cat = categoryDAO.findByCategoryIdAndIsDeleted(id, 0);
		
		if (cat == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(cat, HttpStatus.OK);
		}
		return response;
	}
	
	
	@RequestMapping(value="/categories", method= RequestMethod.GET)
	public ResponseEntity<Category> getCategoryByName(@RequestParam(value = "categoryName") String name){
		
		System.out.println("searching: " + name);
		ResponseEntity<Category> response = null;
		Category cat = categoryDAO.findByCategoryNameAndIsDeleted(name, 0);
		
		if (cat == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(cat, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value="/categories/parents", method= RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategoryParents(){
		System.out.println("category parents");
		ResponseEntity<List<Category>> response = null;
		List<Category> parentCategories = categoryDAO.findCategoryParents();
		
		if (parentCategories == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(parentCategories, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public ResponseEntity<Category> newCategory(@RequestBody Category newCategory){
		System.out.println("creating new category");
		ResponseEntity<Category> response = null;
		Category newCat = categoryDAO.save(newCategory);
		
		if (newCat == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(newCat, HttpStatus.CREATED);
		}
		return response;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.PUT)
	public ResponseEntity<Category> updateCategory(@RequestBody Category categoryToUpdate){
		ResponseEntity<Category> response = null;
		Category updatedCategory = categoryDAO.save(categoryToUpdate);	
		
		if (updatedCategory == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(updatedCategory, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategory(@PathVariable int id){
		System.out.println("updating category " + id);
		ResponseEntity<Category> response = null;
		Category updateCategory = categoryDAO.findByCategoryIdAndIsDeleted(id, 0);
		
		if (updateCategory == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			updateCategory.setIsDeleted(1);
			categoryDAO.save(updateCategory);
			response = new ResponseEntity<>(updateCategory, HttpStatus.OK);
		}
		return response;
	}
}
