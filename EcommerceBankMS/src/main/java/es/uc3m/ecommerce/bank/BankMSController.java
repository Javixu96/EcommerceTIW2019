package es.uc3m.ecommerce.bank;
/*
 * Microservicio Banco, recibe las informaciones de una tarjeta y las comprueba, genera un codigo de confirmacion
 * en caso de que esten correctas todas.
 * */
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.ecommerce.bank.model.*;

@CrossOrigin
@RestController
public class BankMSController {
	
	//generar un objeto de tipo ConfirmationNumber y lo devuelve
	@RequestMapping(value="/bank", method= RequestMethod.POST)
	public ResponseEntity<ConfirmationNumber> processPurchase(@RequestBody PurchaseData purchaseInfo){
		ResponseEntity<ConfirmationNumber> response;
		
		//comprobacion de informaciones de la tarjeta
		if (validateCardInformation(purchaseInfo)) {
			
			//generar el numero aleatorio
			Integer confirmationCode = new Integer(10000 +  + new Random().nextInt(90000));
			ConfirmationNumber cn = new ConfirmationNumber();
			cn.setConfirmationNumber(confirmationCode);
			response = new ResponseEntity<>(cn, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
		}
		return response;
		
	}
	//validar la informacion de la tarjeta
	private boolean validateCardInformation(PurchaseData purchaseInfo) {
		
		String cardNumber = purchaseInfo.getCardNumber();
		Integer exp_month = purchaseInfo.getExpMonth();
		Integer exp_year = purchaseInfo.getExpYear();
		String cvv =  purchaseInfo.getCvv();
		Integer purchaseCost = purchaseInfo.getPurchaseCost();
		//comprobacion 1-divisible entre 3  2-no caducada 3-formato de cvv
		boolean validate = isDivisibleBy3(cardNumber) && isNotExpired(exp_month, exp_year) && cvvCorrect(cvv) && purchaseCost > 0;
		return validate;
	}

	private boolean isDivisibleBy3(String cardNumber) {
		boolean isDivisible;
		int digitSum = 0;
		for(int i = 0; i<cardNumber.length(); i++) {
			digitSum = digitSum + cardNumber.charAt(i)-'0';
		}
		
		if (digitSum % 3 == 0) {
			isDivisible = true;
		} else {
			isDivisible = false;
		}
		return isDivisible;
	}
	
	private boolean isNotExpired(Integer exp_month, Integer exp_year) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		int year = localDate.getYear();
		return exp_year > year || (exp_year == year && exp_month > month);
	}
	
	private boolean cvvCorrect(String cvv) {
		return cvv.length() == 3;
	}
	
}
