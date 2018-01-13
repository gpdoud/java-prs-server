package com.maxtrain.purchaserequest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.UserRepository;
import com.maxtrain.purchaserequest.*;
import com.maxtrain.purchaserequestlineitem.PurchaseRequestLineitem;
import com.maxtrain.purchaserequestlineitem.PurchaseRequestLineitemRepository;
import com.maxtrain.status.StatusRepository;
import com.maxtrain.utility.JsonResponse;

@CrossOrigin
@Controller
@RequestMapping("/PurchaseRequests")
public class PurchaseRequestController {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private PurchaseRequestLineitemRepository purchaseRequestLineitemRepository;

	@GetMapping("/List")
	public @ResponseBody Iterable<PurchaseRequest> List() {
		Iterable<PurchaseRequest> purchaseRequests = purchaseRequestRepository.findAll();
		for(PurchaseRequest purchaseRequest : purchaseRequests) {
			purchaseRequest.setUser(userRepository.findOne(purchaseRequest.getUserId()));
			purchaseRequest.setStatus(statusRepository.findOne(purchaseRequest.getStatusId()));
			Iterable<PurchaseRequestLineitem> lines = purchaseRequestLineitemRepository.findAllByPurchaseRequestId(purchaseRequest.getId());
			purchaseRequest.setPurchaseRequestLineitems((java.util.List<PurchaseRequestLineitem>) lines);
		}
		return purchaseRequests;
	}
	
	@GetMapping("/Get")
	public @ResponseBody Iterable<PurchaseRequest> Get(@RequestParam int id) {
		ArrayList<PurchaseRequest> purchaseRequests = new ArrayList<PurchaseRequest>();
		PurchaseRequest purchaseRequest = purchaseRequestRepository.findOne(id);
		purchaseRequest.setUser(userRepository.findOne(purchaseRequest.getUserId()));
		purchaseRequest.setStatus(statusRepository.findOne(purchaseRequest.getStatusId()));
		Iterable<PurchaseRequestLineitem> lines = purchaseRequestLineitemRepository.findAllByPurchaseRequestId(purchaseRequest.getId());
		purchaseRequest.setPurchaseRequestLineitems((java.util.List<PurchaseRequestLineitem>) lines);
		if(purchaseRequest != null) {
			purchaseRequests.add(purchaseRequest);
		}
		return purchaseRequests;
	}
	
	@PostMapping("/Create")
	public @ResponseBody JsonResponse Create(@RequestBody PurchaseRequest purchaseRequest) {
		purchaseRequest.setSubmitteddate(new Timestamp(System.currentTimeMillis()));
		purchaseRequest.setStatus(statusRepository.findOne(1)); // NEW
		purchaseRequestRepository.save(purchaseRequest);
		return new JsonResponse("Ok", "Successfully created!", "Created!");
	}
	
	@PostMapping("/Change")
	public @ResponseBody JsonResponse Change(@RequestBody PurchaseRequest purchaseRequest) {
		purchaseRequestRepository.save(purchaseRequest);
		return new JsonResponse("Ok", "Successfully changed!", "Changed!");
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse Remove(@RequestBody PurchaseRequest purchaseRequest) {
		purchaseRequestRepository.delete(purchaseRequest);
		return new JsonResponse("Ok", "Successfully removed!", "Removed!");
	}
}
