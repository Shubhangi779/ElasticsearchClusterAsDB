package com.infy1.service;

import java.util.List;

import com.infy1.model.Library;

public interface LibraryService {

	 public void saveLibraryData(List<Library> libraryData);
	 public Iterable<Library> findAllLibraries();
	 public List<Library> findByName(String name);
}
