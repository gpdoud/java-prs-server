package com.maxtrain.purchaserequestlineitem;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.maxtrain.User;
import com.maxtrain.product.Product;
import com.maxtrain.purchaserequest.PurchaseRequest;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="Id")
public class PurchaseRequestLineitem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "PurchaseRequestID")
	@JsonBackReference
	//@JsonIgnore
	private PurchaseRequest purchaseRequest;
	@ManyToOne
	@JoinColumn(name = "ProductID")
	private Product product;
	private int quantity;
	
	public PurchaseRequestLineitem() {}
	public PurchaseRequestLineitem(PurchaseRequest purchaseRequest, Product product, int quantity) {
		this.purchaseRequest = purchaseRequest;
		this.product = product;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PurchaseRequest getPurchaseRequest() {
		return purchaseRequest;
	}
	public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		String msg = String.format("PRLIID: %d; PRID: %d, PID: %d", 
				this.id, 
				this.purchaseRequest != null ? this.purchaseRequest.getId() : -1, 
				this.product != null ? this.product.getId() : -1
		);
		return msg;
	}
	
}
