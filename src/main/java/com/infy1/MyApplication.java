package com.infy1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy1.model.Library;
import com.infy1.repository.LibraryRepository;
import com.infy1.service.LibraryService;
import com.infy1.service.QueryDSLService;


@SpringBootApplication
@ComponentScan(basePackages = "com.infy1.*")
public class MyApplication {  
	
	public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
