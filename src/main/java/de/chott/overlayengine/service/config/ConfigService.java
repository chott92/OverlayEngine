package de.chott.overlayengine.service.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class ConfigService {

	private final Properties properties;

	public ConfigService() {
		properties = new Properties();

		try (FileInputStream input = new FileInputStream("config.properties")) {
			properties.load(input);
		} catch (IOException e) {
			Logger.getLogger(this.getClass().getName()).warn("Could not load Properties", e);
			System.exit(0);
		}
	}

	public String getConfig(String key) {
		return properties.getProperty(key);
	}
}
