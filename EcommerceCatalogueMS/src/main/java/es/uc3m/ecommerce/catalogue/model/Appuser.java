package es.uc3m.ecommerce.catalogue.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the appusers database table.
 * Comentarios en la app de ADMIN
 */
@Entity
@Table(name="appusers")
/*
@NamedQueries({
	@NamedQuery(name="Appuser.findAll", query="SELECT u FROM Appuser u"),
	@NamedQuery(name="Appuser.findByEmail", query="SELECT u FROM Appuser u where u.email = :email AND u.isDeleted = 0"),
	@NamedQuery(name="Appuser.findSellers", query="SELECT a FROM Appuser a WHERE a.userRole = 1 AND a.isDeleted = 0"),
	@NamedQuery(name="Appuser.findBuyers", query="SELECT a FROM Appuser a WHERE a.userRole = 2 AND a.isDeleted = 0"),
})
*/
public class Appuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userId;

	private String email;

	private int isDeleted;

	private String postalAddress;

	private String pw;

	private String userName;

	@Lob
	private byte[] userPicture;


	private int userRole;

	private String userSurnames;

	@JsonIgnore
	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="appuser")
	private List<Product> products;

	@JsonIgnore
	//bi-directional many-to-one association to Purchas
	@OneToMany(mappedBy="appuser")
	private List<Purchas> purchases;

	public Appuser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPostalAddress() {
		return this.postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public byte[] getUserPicture() {
		return this.userPicture;
	}

	public void setUserPicture(byte[] userPicture) {
		this.userPicture = userPicture;
	}

	public int getUserRole() {
		return this.userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public String getUserSurnames() {
		return this.userSurnames;
	}

	public void setUserSurnames(String userSurnames) {
		this.userSurnames = userSurnames;
	}

	public List<Purchas> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchas> purchases) {
		this.purchases = purchases;
	}

	public Purchas addPurchas(Purchas purchas) {
		getPurchases().add(purchas);
		purchas.setAppuser(this);

		return purchas;
	}

	public Purchas removePurchas(Purchas purchas) {
		getPurchases().remove(purchas);
		purchas.setAppuser(null);

		return purchas;
	}

}