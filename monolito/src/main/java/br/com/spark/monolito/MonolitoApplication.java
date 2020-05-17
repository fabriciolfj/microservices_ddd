package br.com.spark.monolito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
public class MonolitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolitoApplication.class, args);
	}

}
