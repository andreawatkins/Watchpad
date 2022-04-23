package com.watchpad.watchpadbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchpadBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchpadBackendApplication.class, args);
		System.out.println("This is Watchpad");
	}

}
