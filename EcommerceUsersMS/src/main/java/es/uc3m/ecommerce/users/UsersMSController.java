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
	
	@RequestMapping(method = RequestMethod.GET, value="/users/buyers/{userId}")
	public ResponseEntity<List<Integer>> findAllConfirmationCode(@PathVariable int userId) {
		List<Integer> allConfirmation = daopurchas.findAllConfirmationCode(userId);
		return new ResponseEntity<>(allConfirmation, HttpStatus.CREATED);
	}

}
