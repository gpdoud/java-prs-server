package com.maxtrain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Users")
public class UserController {

		@Autowired
		private UserRepository userRepository;
		
		@GetMapping("/List")
		public @ResponseBody Iterable<User> List() {
			Iterable<User> users = userRepository.findAll();
			return users;
		}
		
		@GetMapping("/Get")
		public @ResponseBody User Get(@RequestParam int id) {
			User user = userRepository.findOne(id);
			return user;
		}
		
		@PostMapping("/Create")
		public @ResponseBody String Create(@RequestBody User user) {
			userRepository.save(user);
			return "Created!";
		}
		
		@PostMapping("/Change")
		public @ResponseBody String Change(@RequestBody User user) {
			userRepository.save(user);
			return "Modified!";
		}
		
		@PostMapping("/Remove")
		public @ResponseBody String Remove(@RequestBody User user) {
			userRepository.delete(user);
			return "Deleted!";
		}
		

}
