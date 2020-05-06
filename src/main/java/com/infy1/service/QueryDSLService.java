package com.infy1.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.action.count.CountRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
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
	
	 public List<Library> multiMatchQuery(String text) {

	        QueryBuilder query = QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(text).lenient(true).field("name").field("address"))
	        		.should(QueryBuilders.queryStringQuery("*" + text + "*").lenient(true).field("name").field("address"));
	        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(query).build();
	        List<Library> libraries = template.queryForList(build, Library.class);    
	        return libraries;
	    }
	 
		public List<Library> serchWithSpecialChars(String input) {
			input=input.toLowerCase()+"@";
			SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(QueryBuilders.regexpQuery("name", input)).build();
			List<Library> libraryList = template.queryForList(searchQuery, Library.class);			
			return libraryList;

		}
		
		public String customSearchWithFieldType(String text) {
			
			QueryBuilder query = QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(text).lenient(true).field("name").field("address"))
	        		.should(QueryBuilders.queryStringQuery("*" + text + "*").lenient(true).field("name").field("address"));
	        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(query).build();
	        List<Library> libraries =(List<Library>) template.queryForList(build, Library.class);
	        
	        List<String> nameList=libraries.stream().filter(lib -> lib.getName().contains(text)).map(lib -> lib.getName()).collect(Collectors.toList());
	        String nameField="\"Name\":\""+nameList.stream().collect(Collectors.joining("\",\""))+"\",count:"+nameList.size();
	        List<String> addressList=libraries.stream().filter(lib -> lib.getAddress().contains(text)).map(lib -> lib.getAddress()).collect(Collectors.toList());
	        String addressField="\"Address\":\""+addressList.stream().collect(Collectors.joining("\",\""))+"\",count:"+addressList.size();	    
			
			return "{"+"["+nameField+"],["+addressField+"]"+"}";
		}
	
}
