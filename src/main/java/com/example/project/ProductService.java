package com.example.project;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository repo;
	
	public List<Product>listAll(){
		return repo.findAll();
	}
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public Product get(Integer id) {
		return repo.findById(id).get();
	}
	
	public List<Product>getNameofProduct(String nm){
		return repo.findByName(nm);
	}
	
	
	public Product updateProduct(Product p, int id) {
		Product pobj = repo.findById(id).get();
		
		if(Objects.nonNull(p.getName())&& !"".equalsIgnoreCase(p.getName())) {
			pobj.setName(p.getName());
		}
		return repo.save(pobj);
	}
	
	public void delete(Product product) {
        repo.delete(product);
    }

	
}
