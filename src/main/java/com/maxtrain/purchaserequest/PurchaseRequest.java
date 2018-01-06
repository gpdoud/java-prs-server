package com.maxtrain.purchaserequest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.maxtrain.User;
import com.maxtrain.purchaserequestlineitem.PurchaseRequestLineitem;
import com.maxtrain.status.Status;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="Id")
public class PurchaseRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "UserID")
	private User user;
	private String description;
	private String justification;
	private Date dateneeded;
	private String deliverymode;
	@ManyToOne
	@JoinColumn(name = "StatusID")
	private Status status;
	private double total;
	private Date submitteddate;
	@OneToMany(mappedBy = "purchaseRequest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<PurchaseRequestLineitem> purchaseRequestLineitems;
	
	public PurchaseRequest() {}

	public PurchaseRequest(int id, User user, String description, String justification, Timestamp dateneeded,
			String deliverymode, Status status, double total, Timestamp submitteddate,
			List<PurchaseRequestLineitem> purchaseRequestLineitems) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.justification = justification;
		this.dateneeded = dateneeded;
		this.deliverymode = deliverymode;
		this.status = status;
		this.total = total;
		this.submitteddate = submitteddate;
		this.purchaseRequestLineitems = purchaseRequestLineitems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Date getDateneeded() {
		return dateneeded;
	}

	public void setDateneeded(Date dateneeded) {
		this.dateneeded = dateneeded;
	}

	public String getDeliverymode() {
		return deliverymode;
	}

	public void setDeliverymode(String deliverymode) {
		this.deliverymode = deliverymode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getSubmitteddate() {
		return submitteddate;
	}

	public void setSubmitteddate(Timestamp submitteddate) {
		this.submitteddate = submitteddate;
	}

	public List<PurchaseRequestLineitem> getPurchaseRequestLineitems() {
		return purchaseRequestLineitems;
	}

	public void setPurchaseRequestLineitems(List<PurchaseRequestLineitem> purchaseRequestLineitems) {
		this.purchaseRequestLineitems = purchaseRequestLineitems;
	}
	
}
