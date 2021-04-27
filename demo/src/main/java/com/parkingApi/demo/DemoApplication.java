package com.parkingApi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cmeza.sdgenerator.annotation.SDGenerator;  


//@SDGenerator(
//        entityPackage = "com.parkingApi.demo.models",
//        repositoryPackage = "com.parkingApi.demo.repositories",
//        managerPackage = "com.parkingApi.demo.managers",
//        repositoryPostfix = "Repository",
//        managerPostfix = "Manager",
//        onlyAnnotations = false,
//        debug = false,
//        overwrite = false 
//)
@SpringBootApplication  
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	} 

}
