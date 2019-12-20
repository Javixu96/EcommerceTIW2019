package es.uc3m.ecommerce.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uc3m.ecommerce.users.model.*;

@Controller
@CrossOrigin
public class UsersMSController {
	
	@Autowired
	PurchasDAO daopurchas;
	
	@Autowired
	AppuserDAO appuserDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	// Buscar todos los pedidos de un usuario
	@RequestMapping(method = RequestMethod.GET, value="/users/{userId}/purchases")
	public ResponseEntity<List<Integer>> findAllConfirmationCode(@PathVariable int userId) {
		List<Integer> allConfirmation = daopurchas.findAllConfirmationCode(userId);
		
		if(allConfirmation.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(allConfirmation, HttpStatus.OK);
	}
	
	// Buscar todos los productos de un pedido
	@RequestMapping(method = RequestMethod.GET, value="/purchases")
	public ResponseEntity<List<Purchas>> findByConfirmationCode(@RequestParam(value = "confirmationCode") int confirmationCode) {
		List<Purchas> purchases = daopurchas.findByConfirmationCode(confirmationCode);
		return new ResponseEntity<>(purchases, HttpStatus.OK);
	}
		
	// Insertar un pedido
	@RequestMapping(method = RequestMethod.POST, value="/purchases")
	public ResponseEntity<Purchas> insertPurchase(@RequestBody Purchas purchase) {
		Purchas newPurchase = daopurchas.save(purchase);
		return new ResponseEntity<Purchas>(newPurchase, HttpStatus.OK);
	}
	
	// Buscar los compradores
	@RequestMapping(method = RequestMethod.GET, value="/users/buyers")
	public ResponseEntity<List<Appuser>> findAllBuyers(){
		ResponseEntity<List<Appuser>> response;
		List<Appuser> buyers = appuserDAO.findByUserRoleAndIsDeleted(2, 0);
			
		if (buyers == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(buyers, HttpStatus.OK);
		}
		return response;
	}
	
	// Buscar los vendedores
	@RequestMapping(method = RequestMethod.GET, value="/users/sellers")
	public ResponseEntity<List<Appuser>> findAllSellers(){
		ResponseEntity<List<Appuser>> response;
		List<Appuser> sellers = appuserDAO.findByUserRoleAndIsDeleted(1, 0);
			
		if (sellers == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(sellers, HttpStatus.OK);
		}
		return response;
	}
	
	// Buscar todos los usuarios
	@RequestMapping(method = RequestMethod.GET, value="/users")
	public ResponseEntity<List<Appuser>> findAllUsers(){
		ResponseEntity<List<Appuser>> response;
		List<Appuser> sellers = appuserDAO.findByIsDeleted(0);
			
		if (sellers == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(sellers, HttpStatus.OK);
		}
		return response;
	}
	
	// Buscar usuario por su ID
	@RequestMapping(method = RequestMethod.GET, value="/users/{userId}")
	public ResponseEntity<Appuser> findByUserId(@PathVariable int userId) {
		ResponseEntity<Appuser> response = null;
		Appuser user = appuserDAO.findByUserIdAndIsDeleted(userId, 0);
		if (user == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(user, HttpStatus.OK);
		}
		return response;
	}
	
	// Buscar usuario por email y/o password--> tanto para login como registro
	@RequestMapping(method = RequestMethod.GET, value="/users/login")
	public ResponseEntity<Appuser> findByUserId(@RequestParam(value = "email") String email,
			@RequestParam(value = "pw",required = false) String password) {
		ResponseEntity<Appuser> response = null;
		//dependiendo de pw
		Appuser user = (password == null) ? appuserDAO.findByEmailAndIsDeleted(email, 0) : appuserDAO.findByEmailAndPwAndIsDeleted(email, password, 0);
		if (user == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(user, HttpStatus.OK);
		}
		return response;
	}
	
	// Buscar productos de un usuario por su ID
	@RequestMapping(method = RequestMethod.GET, value="/users/{userId}/products")
	public ResponseEntity<List<Product>> findUserProductsByUserId(@PathVariable int userId) {
		ResponseEntity<List<Product>> response = null;
		Appuser user = appuserDAO.findByUserIdAndIsDeleted(userId, 0);
		
		List<Product> userProducts = productDAO.findByAppuserAndIsDeleted(user, 0);
		if (userProducts == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(userProducts, HttpStatus.OK);
		}
		return response;
	}	
		
	// Modificar usuario por su ID 
	@RequestMapping(method = RequestMethod.PUT, value="/users")
	public ResponseEntity<Appuser> modifyUser(@RequestBody Appuser appUser) {
		Appuser user = appuserDAO.save(appUser);
		ResponseEntity<Appuser> response = null;
		if (user == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(user, HttpStatus.OK);
		}
		return response;
	}
		
	// Crear usuario por su ID 
	@RequestMapping(method = RequestMethod.POST, value="/users")
	public ResponseEntity<Appuser> insertUser(@RequestBody Appuser appUser) {
		Appuser user = appuserDAO.save(appUser);
		ResponseEntity<Appuser> response = null;
		if (user == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			response = new ResponseEntity<>(user, HttpStatus.CREATED);
		}
		return response;
	}	
	
	// eliminar un producto (editar el flag isDeleted)
	@RequestMapping(value="/users/{id}", method= RequestMethod.DELETE) 
	public ResponseEntity<Appuser> deleteUser(@PathVariable int id){
		ResponseEntity<Appuser> response = null;
		Appuser p = appuserDAO.findByUserIdAndIsDeleted(id, 0);
		if (p == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			p.setIsDeleted(1);
			appuserDAO.save(p);
			response = new ResponseEntity<>(p, HttpStatus.OK);
		}
		return response;
	}
	

	
	

}
