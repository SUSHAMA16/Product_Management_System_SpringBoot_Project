package com.example.project;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
@Transactional
public class ProductController {

	@Autowired
	ProductService service;
	
	@Autowired
	ProductRepository repo;
	
	//set products value
	@RequestMapping("/setproducts")
	public void saveProducts(@RequestBody Product product) {
		service.save(product);
	}
	
	// get all products
	@RequestMapping("/getproducts")
	public List<Product> getProducts() {
		return service.listAll();
	}
	
	//find products by its id
	@GetMapping("/getproducts/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id){
		
		try {
		Product product = service.get(id);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	//find product by its name
	@GetMapping("products/name")
	public ResponseEntity<List<Product>> getNameofProduct(@RequestParam String name){
		return new ResponseEntity<List<Product>>(repo.findByName(name),HttpStatus.OK);
	}
	
	
	
	//update product name
	@PutMapping("/getproducts/{id}")
	public Product updateProduct(@RequestBody Product p, @PathVariable("id") int id) {
		return service.updateProduct(p,  id);
	}
	
	
	//delete product
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id){
		Optional<Product> pr=this.repo.findById(id);
		if(pr.isPresent()) {
			this.service.delete(pr.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}


