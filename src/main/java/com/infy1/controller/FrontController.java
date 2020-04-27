package com.infy1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy1.model.Library;
import com.infy1.repository.LibraryRepository;

@RestController
@RequestMapping(value="/api")
public class FrontController {

	@Autowired
	LibraryRepository service;
   
	@GetMapping(value= "/test")
	public String showMsg() {
		return "Rest API with Elasticsearch ";
	}
	
   @PostMapping(value = "/saveLibData")
   public String saveLibraryData(@RequestBody List<Library> lib) {
	   service.saveAll(lib);
	   return "Records saved in the db.";
   }
   
   @GetMapping(value= "/getall")
   public Iterable<Library> getAllLibraries() {
       return service.findAll();
   }
    
   @GetMapping(value = "/findByName/name/{name}")
   public List<Library> findLibraryByName(@PathVariable("name") String name){
	   return service.findByName(name);
   }
}
