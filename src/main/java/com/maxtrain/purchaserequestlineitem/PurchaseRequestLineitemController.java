package com.maxtrain.purchaserequestlineitem;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.product.Product;
import com.maxtrain.product.ProductRepository;
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
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/List")
	public @ResponseBody Iterable<PurchaseRequestLineitem> List() {
		Iterable<PurchaseRequestLineitem> items = purchaseRequestLineitemRepository.findAll();
		for(PurchaseRequestLineitem item : items) {
			item.setPurchaseRequest(purchaseRequestRepository.findOne(item.getPurchaseRequestId()));
			item.setProduct(productRepository.findOne(item.getProductId()));
		}
		return items;
	}
	
	@GetMapping("/Get")
	public @ResponseBody Iterable<PurchaseRequestLineitem> Get(@RequestParam int id) {
		ArrayList<PurchaseRequestLineitem> PurchaseRequestLineitems = new ArrayList<PurchaseRequestLineitem>();
		PurchaseRequestLineitem item = purchaseRequestLineitemRepository.findOne(id);
		item.setPurchaseRequest(purchaseRequestRepository.findOne(item.getPurchaseRequestId()));
		item.setProduct(productRepository.findOne(item.getProductId()));
		if(item != null) {
			PurchaseRequestLineitems.add(item);
		}
		return PurchaseRequestLineitems;
	}
	
	@PostMapping("/Create")
	public @ResponseBody JsonResponse Create(@RequestBody PurchaseRequestLineitem PurchaseRequestLineitem) {
		purchaseRequestLineitemRepository.save(PurchaseRequestLineitem);
		RecalcPurchaseRequestTotal(PurchaseRequestLineitem.getPurchaseRequestId());
		return new JsonResponse("Ok", "Successfully created!", "Created! - PurchaseRequest total recalculated.");
	}
	
	@PostMapping("/Change")
	public @ResponseBody JsonResponse Change(@RequestBody PurchaseRequestLineitem PurchaseRequestLineitem) {
		logger.info("Server PRLI: " + PurchaseRequestLineitem.toString());
		purchaseRequestLineitemRepository.save(PurchaseRequestLineitem);
		RecalcPurchaseRequestTotal(PurchaseRequestLineitem.getPurchaseRequest().getId());
		return new JsonResponse("Ok", "Successfully changed!", "Changed! - PurchaseRequest total recalculated.");
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse Remove(@RequestBody PurchaseRequestLineitem PurchaseRequestLineitem) {
		int prid = PurchaseRequestLineitem.getPurchaseRequestId();
		purchaseRequestLineitemRepository.delete(PurchaseRequestLineitem);
		RecalcPurchaseRequestTotal(prid);
		return new JsonResponse("Ok", "Successfully removed!", "Removed! - PurchaseRequest total recalculated.");
	}
	
	private void RecalcPurchaseRequestTotal(int purchaseRequestId) {
		logger.info("Recalculating for purchase request id " + purchaseRequestId);
		double total = 0;
		PurchaseRequest pr = purchaseRequestRepository.findOne(purchaseRequestId);
		pr.setPurchaseRequestLineitems((java.util.List<PurchaseRequestLineitem>) purchaseRequestLineitemRepository.findAllByPurchaseRequestId(purchaseRequestId));
		logger.info("Purchase request has " + pr.getPurchaseRequestLineitems().size() + " lines.");
		for(PurchaseRequestLineitem prli : pr.getPurchaseRequestLineitems()) {
			Product product = productRepository.findOne(prli.getProductId());
			double price = product.getPrice();
			int quantity = prli.getQuantity();
			total += quantity * price;
			logger.info("** Line quantity is " + quantity + " and price is " + price);
		}
		logger.info("New purchase request total is " + total);
		pr.setTotal(total);
		purchaseRequestRepository.save(pr);
	}
}
