package es.uc3m.ecommerce.model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;
import javax.persistence.*;

import es.uc3m.ecommerce.controller.BytesSerializerDeserializer;

import java.util.List;

/**
 * The persistent class for the products database table.
 * Comentarios en la app de ADMIN
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	private int productId;
	
	private int isDeleted;

	private String longDesc;

	private int price;

	private String productName;

	@Lob
	@JsonbTypeDeserializer(BytesSerializerDeserializer.class)
	@JsonbTypeSerializer(BytesSerializerDeserializer.class)
	private byte[] productPicture;

	private String shortDesc;

	private int stock;

	private Appuser appuser;

	private Category categoryBean;

	private List<Purchas> purchases;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Appuser getAppuser() {
		return this.appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
	}

	public Category getCategoryBean() {
		return this.categoryBean;
	}

	public void setCategoryBean(Category categoryBean) {
		this.categoryBean = categoryBean;
	}
	
	public String getLongDesc() {
		return this.longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public byte[] getProductPicture() {
		return this.productPicture;
	}

	public void setProductPicture(byte[] productPicture) {
		this.productPicture = productPicture;
	}

	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Purchas> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchas> purchases) {
		this.purchases = purchases;
	}

	public Purchas addPurchas(Purchas purchas) {
		getPurchases().add(purchas);
		purchas.setProduct(this);

		return purchas;
	}

	public Purchas removePurchas(Purchas purchas) {
		getPurchases().remove(purchas);
		purchas.setProduct(null);

		return purchas;
	}
}