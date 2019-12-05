package es.uc3m.ecommerce.catalogue;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.ecommerce.catalogue.model.Category;
import es.uc3m.ecommerce.catalogue.model.CategoryDAO;

@CrossOrigin
@RestController
public class CatalogueMSController {

	@Autowired
	CategoryDAO categoryDAO;
	
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
