package com.maxtrain.purchaserequestlineitem;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.purchaserequestlineitem.*;
import com.maxtrain.utility.JsonResponse;

@CrossOrigin
@Controller
@RequestMapping("/PurchaseRequestLineitems")
public class PurchaseRequestLineitemController {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PurchaseRequestLineitemRepository purchaseRequestLineitemRepository;

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
		return new JsonResponse("Ok", "Successfully created!", "Created!");
	}
	
	@PostMapping("/Change")
	public @ResponseBody JsonResponse Change(@RequestBody PurchaseRequestLineitem PurchaseRequestLineitem) {
		purchaseRequestLineitemRepository.save(PurchaseRequestLineitem);
		return new JsonResponse("Ok", "Successfully changed!", "Changed!");
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse Remove(@RequestBody PurchaseRequestLineitem PurchaseRequestLineitem) {
		purchaseRequestLineitemRepository.delete(PurchaseRequestLineitem);
		return new JsonResponse("Ok", "Successfully removed!", "Removed!");
	}
}
