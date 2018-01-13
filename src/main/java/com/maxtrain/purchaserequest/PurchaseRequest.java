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
	private int userId;
	@Transient
	private User user;
	private String description;
	private String justification;
	private Date dateneeded;
	private String deliverymode;
	private int statusId;
	@Transient
	private Status status;
	private double total;
	private Date submitteddate;
//	@OneToMany(mappedBy = "purchaseRequest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	@JsonManagedReference
	@Transient
	private List<PurchaseRequestLineitem> purchaseRequestLineitems;
	
	public PurchaseRequest() {}

	public PurchaseRequest(int id, int userId, String description, String justification, Timestamp dateneeded,
			String deliverymode, int statusId, double total, Timestamp submitteddate) {
		super();
		this.id = id;
		this.userId = userId;
		this.description = description;
		this.justification = justification;
		this.dateneeded = dateneeded;
		this.deliverymode = deliverymode;
		this.statusId = statusId;
		this.total = total;
		this.submitteddate = submitteddate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUser(int userId) {
		this.userId = userId;
	}
	
	public User getUser() {
		return this.user;
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

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
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
