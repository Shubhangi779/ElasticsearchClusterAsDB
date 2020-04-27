package com.infy1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy1.model.Library;
import com.infy1.repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepository repository;
	

	    public void saveLibraryData(List<Library> libraryData) {
	        repository.saveAll(libraryData);
	    }
	    
	    
	    public Iterable<Library> findAllLibraries() {
	        return repository.findAll();
	    }
	    
	    public List<Library> findByName(String name) {
	        return repository.findByName(name);
	    }
}
