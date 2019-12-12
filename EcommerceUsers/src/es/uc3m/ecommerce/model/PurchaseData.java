package es.uc3m.ecommerce.model;

public class PurchaseData {

	private String cardNumber;
	private Integer expMonth;
	private Integer expYear;
	private String cvv;
	private Integer purchaseCost;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public Integer getPurchaseCost() {
		return purchaseCost;
	}
	public void setPurchaseCost(Integer purchaseCost) {
		this.purchaseCost = purchaseCost;
	}
	public Integer getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(Integer expMonth) {
		this.expMonth = expMonth;
	}
	public Integer getExpYear() {
		return expYear;
	}
	public void setExpYear(Integer expYear) {
		this.expYear = expYear;
	}	
}
