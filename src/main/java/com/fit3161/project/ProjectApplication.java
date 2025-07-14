package com.fit3161.project; // ✅ your actual package name
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct; // ✅ MUST be jakarta.annotation (not javax)

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		System.out.println("🔍 ENV DEBUG >>>");
		System.out.println("🔍 DB_HOST = " + System.getenv("DB_HOST"));
		System.out.println("🔍 DB_USER = " + System.getenv("DB_USER"));
		System.out.println("🔍 DB_PASSWORD = " + System.getenv("DB_PASSWORD"));
		System.out.println("🔍 MAIL_USER = " + System.getenv("MAIL_USER"));
		System.out.println("🔍 JWT_SECRET = " + System.getenv("JWT_SECRET"));
		System.out.println("🔍 <<< END ENV DEBUG");
		SpringApplication.run(ProjectApplication.class, args);
	}

	@PostConstruct
	public void logEnv() {
		System.out.println("🔍 DB_HOST = " + System.getenv("DB_HOST"));
	}
}