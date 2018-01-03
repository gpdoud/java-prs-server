package com.maxtrain.purchaserequestlineitem;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.purchaserequest.PurchaseRequest;
import com.maxtrain.purchaserequest.PurchaseRequestRepository;
import com.maxtrain.purchaserequestlineitem.*;
import com.maxtrain.utility.JsonResponse;

@CrossOrigin
@Controller
@RequestMapping("/PurchaseRequestLineitems")
public class PurchaseRequestLineitemController {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PurchaseRequestLineitemRepository purchaseRequestLineitemRepository;
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;

	@GetMapping("/List")
	public @ResponseBody Iterable<PurchaseRequestLineitem> List() {
		return purchaseRequestLineitemRepository.findAll();
	}
	
	@GetMapping("/Get")
	public @ResponseBody Iterable<PurchaseRequestLineitem> Get(@RequestParam int id) {
		ArrayList<PurchaseRequestLineitem> PurchaseRequestLineitems = new ArrayList<PurchaseRequestLineitem>();
		PurchaseRequestLineitem PurchaseRequestLineitem = purchaseRequestLineitemRepository.findOne(id);
		if(PurchaseRequestLineitem != null) {
			PurchaseRequestLineitems.add(PurchaseRequestLineitem);
		}
		return PurchaseRequestLineitems;
	}
	
	@PostMapping("/Create")
	public @ResponseBody JsonResponse Create(@RequestBody PurchaseRequestLineitem PurchaseRequestLineitem) {
		purchaseRequestLineitemRepository.save(PurchaseRequestLineitem);
		RecalcPurchaseRequestTotal(PurchaseRequestLineitem.getPurchaseRequest().getId());
		return new JsonResponse("Ok", "Successfully created!", "Created! - PurchaseRequest total recalculated.");
	}
	
	@PostMapping("/Change")
	public @ResponseBody JsonResponse Change(@RequestBody PurchaseRequestLineitem PurchaseRequestLineitem) {
		purchaseRequestLineitemRepository.save(PurchaseRequestLineitem);
		RecalcPurchaseRequestTotal(PurchaseRequestLineitem.getPurchaseRequest().getId());
		return new JsonResponse("Ok", "Successfully changed!", "Changed! - PurchaseRequest total recalculated.");
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse Remove(@RequestBody PurchaseRequestLineitem PurchaseRequestLineitem) {
		purchaseRequestLineitemRepository.delete(PurchaseRequestLineitem);
		RecalcPurchaseRequestTotal(PurchaseRequestLineitem.getPurchaseRequest().getId());
		return new JsonResponse("Ok", "Successfully removed!", "Removed! - PurchaseRequest total recalculated.");
	}
	
	private void RecalcPurchaseRequestTotal(int purchaseRequestId) {
		logger.info("Recalculating for purchase request id " + purchaseRequestId);
		double total = 0;
		PurchaseRequest pr = purchaseRequestRepository.findOne(purchaseRequestId);
		logger.info("Purchase request has " + pr.getPurchaseRequestLineitems().size() + " lines.");
		for(PurchaseRequestLineitem prli : pr.getPurchaseRequestLineitems()) {
			total += prli.getQuantity() * prli.getProduct().getPrice();
			logger.info("** Line quantity is " + prli.getQuantity() + " and price is " + prli.getProduct().getPrice());
		}
		logger.info("New purchase request total is " + total);
		pr.setTotal(total);
		purchaseRequestRepository.save(pr);
	}
}
