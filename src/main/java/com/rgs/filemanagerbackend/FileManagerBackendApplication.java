package com.rgs.filemanagerbackend;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Api
public class FileManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagerBackendApplication.class, args);
	}

}
