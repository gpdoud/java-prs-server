package com.maxtrain.vendor;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.User;
import com.maxtrain.utility.JsonResponse;

@RestController
@RequestMapping("/Vendors")
public class VendorController {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VendorRepository vendorRepository;
	
	@GetMapping("/List")
	public @ResponseBody Iterable<Vendor> List() {
		return vendorRepository.findAll();
	}
	
	@GetMapping("/Get")
	public @ResponseBody Iterable<Vendor> Get(@RequestParam int id) {
		ArrayList<Vendor> vendors = new ArrayList<Vendor>();
		Vendor vendor = vendorRepository.findOne(id);
		if(vendor != null) {
			vendors.add(vendor);
		}
		return vendors;
	}
	
	@PostMapping("/Create")
	public @ResponseBody JsonResponse Create(@RequestBody Vendor vendor) {
		vendor.setDatecreated(new Date());
		vendorRepository.save(vendor);
		return new JsonResponse("Ok", "Successfully created!", "Created!");
	}
	
	@PostMapping("/Change")
	public @ResponseBody JsonResponse Change(@RequestBody Vendor vendor) {
		vendorRepository.save(vendor);
		return new JsonResponse("Ok", "Successfully changed!", "Changed!");
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse Remove(@RequestBody Vendor vendor) {
		vendorRepository.delete(vendor);
		return new JsonResponse("Ok", "Successfully removed!", "Removed!");
	}
	



}
