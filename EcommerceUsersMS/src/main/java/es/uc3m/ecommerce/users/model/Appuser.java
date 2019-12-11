package es.uc3m.ecommerce.users.model;

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
public class Appuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="userId")
	private int userId;
	
	@Column(name="email")
	private String email;

	@Column(name="isDeleted")
	private int isDeleted;

	@Column(name="postalAddress")
	private String postalAddress;

	@Column(name="pw")
	private String pw;
	
	@Column(name="userName")
	private String userName;

	@Lob
	@Column(name="userPicture")
	private byte[] userPicture;

	@Column(name="userRole")
	private int userRole;
	
	@Column(name="userSurnames")
	private String userSurnames;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="appuser")
	private List<Product> products;

	//bi-directional many-to-one association to Purchas
	@OneToMany(mappedBy="appuser")
	@JsonIgnore
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