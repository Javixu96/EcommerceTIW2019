package es.uc3m.ecommerce.chat.model;

import java.io.Serializable;
import javax.persistence.*;
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
	@Column(name="userid")
	private int userId;

	private String email;

	@Column(name="isdeleted")
	private int isDeleted;

	@Column(name="postaladdress")
	private String postalAddress;

	private String pw;

	@Column(name="username")
	private String userName;

	@Column(name="userpicture")
	private byte[] userPicture;

	@Column(name="userrole")
	private int userRole;

	@Column(name="usersurnames")
	private String userSurnames;

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

}