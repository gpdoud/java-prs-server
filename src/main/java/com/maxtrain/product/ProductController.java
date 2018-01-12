package com.maxtrain.product;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.utility.JsonResponse;
import com.maxtrain.vendor.Vendor;
import com.maxtrain.vendor.VendorRepository;

@CrossOrigin
@Controller
@RequestMapping("/Products")
public class ProductController {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private VendorRepository vendorRepository;

	@GetMapping("/List")
	public @ResponseBody Iterable<Product> List() {
		Iterable<Product> products = productRepository.findAll();
		for(Product product : products) {
			product.setVendor(getVendorById(product.getVendorId()));
		}
		return products;
	}
	
	@GetMapping("/Get")
	public @ResponseBody Iterable<Product> Get(@RequestParam int id) {
		ArrayList<Product> products = new ArrayList<Product>();
		Product product = productRepository.findOne(id);
		product.setVendor(getVendorById(product.getVendorId()));
		if(product != null) {
			products.add(product);
		}
		return products;
	}
	
	private Vendor getVendorById(int id) {
		return vendorRepository.findOne(id);
	}
	
	@PostMapping("/Create")
	public @ResponseBody JsonResponse Create(@RequestBody Product product) {
		productRepository.save(product);
		return new JsonResponse("Ok", "Successfully created!", "Created!");
	}
	
	@PostMapping("/Change")
	public @ResponseBody JsonResponse Change(@RequestBody Product product) {
		productRepository.save(product);
		return new JsonResponse("Ok", "Successfully changed!", "Changed!");
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse Remove(@RequestBody Product product) {
		productRepository.delete(product);
		return new JsonResponse("Ok", "Successfully removed!", "Removed!");
	}
	
}
