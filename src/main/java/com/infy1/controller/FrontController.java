package com.infy1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.infy1.model.Library;
import com.infy1.repository.LibraryRepository;
import com.infy1.service.QueryDSLService;

@RestController
@RequestMapping(value="/api")
public class FrontController {

	@Autowired
	LibraryRepository service;
	@Autowired
	private QueryDSLService queryService;

	@GetMapping(value= "/test")
	public String showMsg() {
		return "Rest API with Elasticsearch ";
	}

	@PostMapping(value = "/saveLibData")
	public String saveLibraryData(@RequestBody List<Library> lib) {
		service.save(lib);
		return "Records saved in the db.";
	}

	@GetMapping(value= "/getall")
	public Iterable<Library> getAllLibraries() {
		return Lists.newArrayList(service.findAll());
	}

	@GetMapping(value = "/findByName/name/{name}")
	public List<Library> findLibraryByName(@PathVariable("name") String name){
		return service.findByName(name);
	}

	@GetMapping("/serachMultiField/{name}/{address}")
	public List<Library> serachByMultiField(@PathVariable String name, @PathVariable String address) {
		return queryService.searchMultiField(name, address);
	}

	@GetMapping("/customSearch/{input}")
	public List<Library> getLibraryByField(@PathVariable String input) {
		return queryService.multiMatchQuery(input);
	}
	
	@GetMapping("/searchWithSpecialChar/{input}")
	public List<Library> getLibraryWithSpecialChar(@PathVariable String input){
		return queryService.serchWithSpecialChars(input);
	}
	
	@GetMapping("/customSearchCount/{input}")
	public String getSearchWithFieldType(@PathVariable String input) {
		return queryService.customSearchWithFieldType(input);
		
	}

}
