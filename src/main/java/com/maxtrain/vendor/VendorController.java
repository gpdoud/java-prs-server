package com.maxtrain.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Vendors")
public class VendorController {
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@GetMapping("/List")
	public @ResponseBody Iterable<Vendor> List() {
		Iterable<Vendor> vendors = vendorRepository.findAll();
		return vendors;
	}
	
	@GetMapping("/Get")
	public @ResponseBody Vendor Get(@RequestParam int id) {
		Vendor vendor = vendorRepository.findOne(id);
		return vendor;
	}
	
	@PostMapping("/Create")
	public @ResponseBody String Create(@RequestBody Vendor vendor) {
		vendorRepository.save(vendor);
		return "Created!";
	}
	
	@PostMapping("/Change")
	public @ResponseBody String Change(@RequestBody Vendor vendor) {
		vendorRepository.save(vendor);
		return "Modified!";
	}
	
	@PostMapping("/Remove")
	public @ResponseBody String Remove(@RequestBody Vendor vendor) {
		vendorRepository.delete(vendor);
		return "Deleted!";
	}
	



}
