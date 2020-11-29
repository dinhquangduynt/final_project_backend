package com.dinhquangduy.electronic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dinhquangduy.electronic.services.ImageStorageService;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableConfigurationProperties
public class FinalProjectApplication implements CommandLineRunner{
    @Autowired
    private ImageStorageService imageStorageService;
	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}
    @Override
    public void run(String... args) throws Exception {
        imageStorageService.init();
        
    }
	
}
