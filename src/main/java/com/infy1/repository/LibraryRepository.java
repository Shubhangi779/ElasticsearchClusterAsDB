package com.infy1.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy1.model.Library;

@Repository
public interface LibraryRepository extends  ElasticsearchRepository<Library, Integer>{
	
	public List<Library> findByName(String name);
     
}
