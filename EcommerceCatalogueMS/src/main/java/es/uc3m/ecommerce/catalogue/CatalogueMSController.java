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
	@RequestMapping(value="/products/{id}", method= RequestMethod.GET) // buscar producto por su ID
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
	
	@RequestMapping(value="/products", method= RequestMethod.GET) // buscar todos los productos
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
	
	@RequestMapping(value="/products", method= RequestMethod.GET) // buscar todos los productos filtrados por campos
	public ResponseEntity<List<Product>> findAllProductsSearch(
			@RequestParam(value="productName", required=false) String productName,
			@RequestParam(value="shortDesc", required=false) String shortDec,
			@RequestParam(value="categoryBean", required=false) Category categoryBean, 
			@RequestParam(value="price", required=false) int price){
		
		ResponseEntity<List<Product>> response = null;
		
		if(productName == null) {
			
		}
		List<Product> pList = (List<Product>) productDAO.findAll();
		
		if (pList == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(pList, HttpStatus.OK);
		}
		return response;
	}
	
	//PRODUCT - LLAMADAS POST
	@RequestMapping(value="/products", method= RequestMethod.POST) // insertar un nuevo producto
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
	
	//PRODUCT - LLAMADAS PUT
	@RequestMapping(value="/products/{id}", method= RequestMethod.PUT) // editar un producto existente
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
	
	//PRODUCT - LLAMADAS DELETE
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
	
	
	@RequestMapping(value="/categories/{id}", method= RequestMethod.GET)
	public ResponseEntity<Category> processPurchase(@PathVariable int id){
		
		ResponseEntity<Category> response = null;
		Category cat = categoryDAO.findById(id).orElse(null);
		
		if (cat == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(cat, HttpStatus.OK);
		}
		return response;
	}
	
}
