package com.hhgs.plantshows;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@MapperScan(basePackages = "com.hhgs.plantshows.mapper")
@EnableScheduling
public class PlantShowsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantShowsApplication.class, args);
	}

}
