package es.uc3m.ecommerce.bank;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BankMSController {

	@RequestMapping(value="/bank", method= RequestMethod.POST)
	public ResponseEntity<Integer> processPurchase(@RequestBody Map<String, Object> purchaseInfo){
		ResponseEntity<Integer> response;
		
		if (validateCardInformation(purchaseInfo)) {
			Integer confirmationCode = new Integer(10000 +  + new Random().nextInt(90000));
			response = new ResponseEntity<>(confirmationCode, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
		}
		return response;
		
	}
	
	private boolean validateCardInformation(Map<String, Object> purchaseInfo) {
		
		String cardNumber = (String) purchaseInfo.get("card_number");
		Integer exp_month = (Integer) purchaseInfo.get("expiration_month");
		Integer exp_year = (Integer) purchaseInfo.get("expiration_year");
		String cvv = (String) purchaseInfo.get("CVV");
		Double purchaseCost = (Double) purchaseInfo.get("purchaseCost");
		boolean validate = isDivisibleBy3(cardNumber) && isNotExpired(exp_month, exp_year) && cvvCorrect(cvv) && purchaseCost > 0.0;
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
