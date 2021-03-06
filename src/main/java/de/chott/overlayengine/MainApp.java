/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.chott.overlayengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"de.chott.overlayengine"})
@EnableScheduling
public class MainApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
}
