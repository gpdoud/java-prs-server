package com.maxtrain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private boolean reviewer;
	private boolean admin;
	private boolean active;
	private Date datecreated;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public boolean isReviewer() {
		return reviewer;
	}



	public void setReviewer(boolean reviewer) {
		this.reviewer = reviewer;
	}



	public boolean isAdmin() {
		return admin;
	}



	public void setAdmin(boolean admin) {
		this.admin = admin;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public Date getDatecreated() {
		return datecreated;
	}



	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}



	public User(
		String username,
		String password,
		String firstname,
		String lastname,
		String phone,
		String email,
		boolean reviewer,
		boolean admin
	) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.reviewer = reviewer;
		this.admin = admin;
		this.active = true;
		this.datecreated = new Date();
	}
	
	public User() {
		super();
	}

}
