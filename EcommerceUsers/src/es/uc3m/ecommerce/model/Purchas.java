package es.uc3m.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the purchases database table.
 * Comentarios en la app de ADMIN
 */
public class Purchas implements Serializable {
	private static final long serialVersionUID = 1L;

	private int purchaseId;

	private int confirmationCode;

	private int productQuantity;

	private Appuser appuser;

	private Product product;

	public Purchas() {
	}

	public int getPurchaseId() {
		return this.purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getConfirmationCode() {
		return this.confirmationCode;
	}

	public void setConfirmationCode(int confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public int getProductQuantity() {
		return this.productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Appuser getAppuser() {
		return this.appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}