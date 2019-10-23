package servlet;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int productId;

	private int category;

	private String imageurl;

	private String longDesc;

	private String name;

	private int price;

	private String productName;

	@Lob
	private byte[] productPicture;

	private String shortDesc;

	private int stock;

	private int subcategory;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="sellerId")
	private Appuser appuser1;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="userId")
	private Appuser appuser2;

	public Product() {
	}

	public int getproductId() {
		return this.productId;
	}

	public void setproductId(int productId) {
		this.productId = productId;
	}

	public int getCategory() {
		return this.category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getLongDesc() {
		return this.longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getSubcategory() {
		return this.subcategory;
	}

	public void setSubcategory(int subcategory) {
		this.subcategory = subcategory;
	}

	public Appuser getAppuser1() {
		return this.appuser1;
	}

	public void setAppuser1(Appuser appuser1) {
		this.appuser1 = appuser1;
	}

	public Appuser getAppuser2() {
		return this.appuser2;
	}

	public void setAppuser2(Appuser appuser2) {
		this.appuser2 = appuser2;
	}

}