package es.uc3m.ecommerce.catalogue.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * La clase de persistencia para la tabla "categories"  
 */
@Entity
@Table(name="categories")
/*@NamedQueries({
	@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c WHERE c.isDeleted = 0"),
	@NamedQuery(name="Category.findCount", query="SELECT Count(c) FROM Category c WHERE c.isDeleted = 0"),
	@NamedQuery(name="Category.findById", query="SELECT c FROM Category c WHERE c.categoryId = :categoryId AND c.isDeleted = 0"),
	@NamedQuery(name="Category.findRootCategories",
		query="SELECT c FROM Category c WHERE c.categoryId in (SELECT DISTINCT c.categoryId FROM Category c WHERE c.category.categoryId = 1) AND c.category.categoryId IS NOT NULL AND c.isDeleted = 0")
})*/
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //los ids de nuevas categorias se generan automaticamente
	private int categoryId;

	private String categoryName;
	
	private int isDeleted;

	//bi-directional many-to-one association to Category
	//la categoria Padre se guarda no como Id, sino como objeto categoria, de tal modo que se puede acceder a todos sus atributos
	
	 // @ManyToOne
	  
	  //@JoinColumn(name="parentId") 
	 // private Category category;
	 
	@Column(name="parentId") 
	private Integer parentId; 
	
	
	//bi-directional many-to-one association to Category
	//en el caso de las categorias padre, se puede acceder a la lista de categorias hijas a traves de este atributo
	@OneToMany(mappedBy="parentId")
	private List<Category> categories;

	//bi-directional many-to-one association to Product
	//tambien se puede acceder a la lista de productos de una misma categoria
	
	@JsonIgnore
	@OneToMany(mappedBy="categoryBean")
	private List<Product> products;

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/*
	 * public Category getCategory() { return this.category; }
	 * 
	 * public void setCategory(Category category) { this.category = category; }
	 */

	public List<Category> getCategories() {
		return this.categories;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setParentId(this.getCategoryId());
		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setParentId(null);

		return category;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategoryBean(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategoryBean(null);

		return product;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

}