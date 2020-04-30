package com.infy1.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.infy1.model.Library;

@Service
public class QueryDSLService {
	
	@Autowired
	private ElasticsearchTemplate template;
	
	public List<Library> searchMultiField(String name, String address ){
		QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", name)).must(QueryBuilders.matchQuery("address", address));
	    NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
	    List<Library> libraries = template.queryForList(searchQuery, Library.class);
	    return libraries;
	}
	
	public List<Library> librarySearchData(String input){
		String search = ".*"+input+".*";
		NativeSearchQuery query = new NativeSearchQueryBuilder().withFilter(QueryBuilders.regexpQuery("input", search)).build();
		List<Library> libraries = template.queryForList(query, Library.class);
		return libraries;
	}
	
}
