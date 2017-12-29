package com.maxtrain.purchaserequestlineitem;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.purchaserequest.*;
import com.maxtrain.utility.JsonResponse;

@CrossOrigin
@Controller
@RequestMapping("/Request")
public class PurchaseRequestLineitemController {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;

	@GetMapping("/List")
	public @ResponseBody Iterable<PurchaseRequest> List() {
		return purchaseRequestRepository.findAll();
	}
	
	@GetMapping("/Get")
	public @ResponseBody Iterable<PurchaseRequest> Get(@RequestParam int id) {
		ArrayList<PurchaseRequest> purchaseRequests = new ArrayList<PurchaseRequest>();
		PurchaseRequest purchaseRequest = purchaseRequestRepository.findOne(id);
		if(purchaseRequest != null) {
			purchaseRequests.add(purchaseRequest);
		}
		return purchaseRequests;
	}
	
	@PostMapping("/Create")
	public @ResponseBody JsonResponse Create(@RequestBody PurchaseRequest purchaseRequest) {
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
