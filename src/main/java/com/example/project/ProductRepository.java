package com.example.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

	Optional<Product> findById(long id);
	
	List<Product> findByName(String name);
	
	
}
