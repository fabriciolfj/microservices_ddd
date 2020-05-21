package br.com.spark.dashhytrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DashHytrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashHytrixApplication.class, args);
	}

}
