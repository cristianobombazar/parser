package com.ef.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
public class ParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParserApplication.class, args);
	}

	@PostConstruct
	public void timeZone(){
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("-05:00")));
	}

}