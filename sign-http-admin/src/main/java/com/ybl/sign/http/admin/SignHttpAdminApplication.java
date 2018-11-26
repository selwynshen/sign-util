package com.ybl.sign.http.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SignHttpAdminApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SignHttpAdminApplication.class, args);
	}


	@Bean
	public RestTemplate customRestTemplate(){
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		int timeout = 30 * 1000;
		httpRequestFactory.setConnectionRequestTimeout(timeout);
		httpRequestFactory.setConnectTimeout(timeout);
		httpRequestFactory.setReadTimeout(timeout);

		return new RestTemplate(httpRequestFactory);
	}
}
