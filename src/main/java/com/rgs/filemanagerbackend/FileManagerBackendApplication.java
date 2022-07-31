package com.rgs.filemanagerbackend;

import com.rgs.filemanagerbackend.config.DirectoryProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({DirectoryProperty.class})
public class FileManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagerBackendApplication.class, args);
	}

}
