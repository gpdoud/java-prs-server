package com.maxtrain;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.utility.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping("/Users")
public class UserController {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

		@Autowired
		private UserRepository userRepository;
		
		@PostMapping("/Authenticate")
		public @ResponseBody Iterable<User> Authenticate(@RequestBody User user) {
			return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		}
		
		@PostMapping("/About")
		public @ResponseBody JsonResponse About(@RequestBody JsonResponse param) {
			return param;
		}
		
		@GetMapping("/List")
		public @ResponseBody Iterable<User> List() {
			Iterable<User> users = userRepository.findAll();
			return users;
		}
		
		@GetMapping("/Get")
		public @ResponseBody Iterable<User> Get(@RequestParam int id) {
			ArrayList<User> users = new ArrayList<User>();
			User user = userRepository.findOne(id);
			if(user != null) {
				users.add(user);
			}
			return users;
		}
		
		@PostMapping("/Create")
		public @ResponseBody JsonResponse Create(@RequestBody User user) {
			user.setDatecreated(new Date());
			userRepository.save(user);
			return new JsonResponse("Ok", "Successfully created!", "Created!");
		}

		@PostMapping("/Change")
		public @ResponseBody JsonResponse Change(@RequestBody User user) {
			userRepository.save(user);
			logger.info(user.getUsername());
			return new JsonResponse("Ok", "Successfully changed!", "Modified!");
		}
		
		@PostMapping("/Remove")
		public @ResponseBody JsonResponse Remove(@RequestBody User user) {
			userRepository.delete(user);
			return new JsonResponse("Ok", "Successfully deleted!", "Deleted!");
		}
		

}


