package com.maxtrain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

	Iterable<User> findByUsernameAndPassword(String username, String password);
	
}
