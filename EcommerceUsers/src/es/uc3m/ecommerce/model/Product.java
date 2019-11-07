package es.uc3m.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef


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
<<<<<<< HEAD
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productid;

	private String category;
=======
	private int productId;
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef

	private String longDesc;

	private int price;

	private String productName;

	@Lob
	private byte[] productPicture;

<<<<<<< HEAD
	private int sellerId;

=======
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
	private String shortDesc;

	private int stock;

<<<<<<< HEAD
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
=======
	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="sellerId")
	private Appuser appuser;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category")
	private Category categoryBean;

	//bi-directional many-to-one association to Purchas
	@OneToMany(mappedBy="product")
	private List<Purchas> purchases;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
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

<<<<<<< HEAD
	public int getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

=======
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
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

<<<<<<< HEAD
	public int getSubcategory() {
		return this.subcategory;
	}

	public void setSubcategory(int subcategory) {
		this.subcategory = subcategory;
	}
/*
=======
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
	public Appuser getAppuser() {
		return this.appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
<<<<<<< HEAD
	}*/
/*
=======
	}

>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
	public Category getCategoryBean() {
		return this.categoryBean;
	}

	public void setCategoryBean(Category categoryBean) {
		this.categoryBean = categoryBean;
	}
<<<<<<< HEAD
*/
=======

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

>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
}