package es.uc3m.ecommerce.model;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productid;

	private String category;

	private String longDesc;

	private int price;

	private String productName;

	@Lob
	private byte[] productPicture;

	private int sellerId;

	private String shortDesc;

	private int stock;

	private int subcategory;

	//bi-directional many-to-one association to Appuser
	//@ManyToOne
	//@JoinColumn(name="userId", referencedColumnName="userId")
	//private Appuser appuser;

	//bi-directional many-to-one association to Category
	/*
	@ManyToOne
	@JoinColumn(name="category")
	private Category categoryBean;
	*/
	public Product() {
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public int getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
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
/*
	public Appuser getAppuser() {
		return this.appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
	}*/
/*
	public Category getCategoryBean() {
		return this.categoryBean;
	}

	public void setCategoryBean(Category categoryBean) {
		this.categoryBean = categoryBean;
	}
*/
}