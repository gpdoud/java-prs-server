package com.maxtrain.purchaserequestlineitem;

import javax.persistence.*;

import com.maxtrain.User;
import com.maxtrain.purchaserequest.PurchaseRequest;

@Entity
public class PurchaseRequestLineitem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "PurchaseRequestID")
	private PurchaseRequest purchaserequest;
	
	public PurchaseRequestLineitem() {}
}
