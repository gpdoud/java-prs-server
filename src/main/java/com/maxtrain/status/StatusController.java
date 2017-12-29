package com.maxtrain.status;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.utility.JsonResponse;

@CrossOrigin
@Controller
@RequestMapping("/Statuses")
public class StatusController {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StatusRepository statusRepository;

	@GetMapping("/List")
	public @ResponseBody Iterable<Status> List() {
		return statusRepository.findAll();
	}
	
	@GetMapping("/Get")
	public @ResponseBody Iterable<Status> Get(@RequestParam int id) {
		ArrayList<Status> statuses = new ArrayList<Status>();
		Status status = statusRepository.findOne(id);
		if(status != null) {
			statuses.add(status);
		}
		return statuses;
	}
	
	@PostMapping("/Create")
	public @ResponseBody JsonResponse Create(@RequestBody Status status) {
		statusRepository.save(status);
		return new JsonResponse("Ok", "Successfully created!", "Created!");
	}
	
	@PostMapping("/Change")
	public @ResponseBody JsonResponse Change(@RequestBody Status status) {
		statusRepository.save(status);
		return new JsonResponse("Ok", "Successfully changed!", "Changed!");
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse Remove(@RequestBody Status status) {
		statusRepository.delete(status);
		return new JsonResponse("Ok", "Successfully removed!", "Removed!");
	}
}
