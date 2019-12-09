package es.uc3m.ecommerce.users;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import es.uc3m.ecommerce.users.model.*;

@Controller
@CrossOrigin
public class UsersMSController {
	
	@Autowired
	PurchasDAO daopurchas;
	
	@Autowired
	AppuserDAO daoappuser;
	
	// Buscar usuario por su ID
	@RequestMapping(method = RequestMethod.GET, value="/users/{userId}")
	public ResponseEntity<Appuser> findByUserId(@PathVariable int userId) {
		Appuser user = daoappuser.findByUserId(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	// Buscar todos los pedidos de un usuario
	@RequestMapping(method = RequestMethod.GET, value="/users/buyers/{userId}")
	public ResponseEntity<List<Integer>> findAllConfirmationCode(@PathVariable int userId) {
		List<Integer> allConfirmation = daopurchas.findAllConfirmationCode(userId);
		return new ResponseEntity<>(allConfirmation, HttpStatus.OK);
	}
	
	// Buscar todos los productos de un pedido
	@RequestMapping(method = RequestMethod.GET, value="/users/purchases/{confirmationCode}")
	public ResponseEntity<List<Purchas>> findByConfirmationCode(@PathVariable int confirmationCode) {
		List<Purchas> purchases = daopurchas.findByConfirmationCode(confirmationCode);
		return new ResponseEntity<>(purchases, HttpStatus.OK);
	}
		
	// Insertar un pedido
	@RequestMapping(method = RequestMethod.POST, value="/users/purchases")
	public ResponseEntity<Purchas> insertPurchase(@RequestBody Purchas purchase) {
		Purchas newPurchase = daopurchas.save(purchase);
		return new ResponseEntity<>(newPurchase, HttpStatus.OK);
	}
}
