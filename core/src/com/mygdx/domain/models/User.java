package com.mygdx.domain.models;

/**
 * Classe Bean della tabella users.
 * La password la salviamo solo su db.
 * @author anton
 */
public class User {

	private String idUser;
	private String nickName;
	private String email;
	private String telefonNumber;
	private String pathImage;
	private boolean isAdministrator;

	public User() {
		idUser = "";
		nickName = "";
		email = "";
		telefonNumber = "";
		pathImage = "";
		isAdministrator = false;
	}

	public User(String email, String nickName, String telefonNumber, String pathImage, boolean isAdministrator) {
		this.email = email;
		this.nickName = nickName;
		this.telefonNumber = telefonNumber;
		this.pathImage = pathImage;
		this.isAdministrator = isAdministrator;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}

	public void setAdministrator(boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonNumber() {
		return telefonNumber;
	}

	public void setTelefonNumber(String telefonNumber) {
		this.telefonNumber = telefonNumber;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	
	
}