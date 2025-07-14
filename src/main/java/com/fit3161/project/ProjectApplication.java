package com.fit3161.project; // ✅ your actual package name
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct; // ✅ MUST be jakarta.annotation (not javax)

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@PostConstruct
	public void logEnv() {
		System.out.println("🔍 DB_HOST = " + System.getenv("DB_HOST"));
	}
}