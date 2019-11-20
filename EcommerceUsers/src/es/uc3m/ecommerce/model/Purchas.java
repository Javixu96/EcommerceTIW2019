package es.uc3m.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the purchases database table.
 * 
 */
@Entity
@Table(name="purchases")
@NamedQuery(name="Purchas.findAll", query="SELECT p FROM Purchas p")
@NamedQuery(name="Purchas.findAllConfirmationCode", query="SELECT DISTINCT p.confirmationCode FROM Purchas p where p.appuser= :user")
@NamedQuery(name="Purchas.findPurchase", query="SELECT p FROM Purchas p WHERE p.confirmationCode = :confirmationCode")
public class Purchas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int purchaseId;

	private int confirmationCode;

	private int productQuantity;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="buyerId")
	private Appuser appuser;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productId")
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