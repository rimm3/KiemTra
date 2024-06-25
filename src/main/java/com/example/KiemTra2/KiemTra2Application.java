package com.example.KiemTra2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.KiemTra2")
public class KiemTra2Application {

	public static void main(String[] args) {
		SpringApplication.run(KiemTra2Application.class, args);
	}

}
