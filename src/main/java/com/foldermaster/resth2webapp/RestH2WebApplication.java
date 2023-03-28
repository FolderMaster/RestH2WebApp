package com.foldermaster.resth2webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Класс приложения с методом запуска.
 */
@SpringBootApplication
public class RestH2WebApplication {
	/**
	 * Запускает приложение.
	 * @param args Аргументы.
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RestH2WebApplication.class, args);
	}
}