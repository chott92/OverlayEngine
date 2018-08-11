package de.chott.overlayengine.service.horaro;

import java.util.Map;

public class HoraroImportConfig {

	private String scheduleURL;
	private String properties;

	public HoraroImportConfig() {
	}

	public HoraroImportConfig(String scheduleURL, String properties) {
		this.scheduleURL = scheduleURL;
		this.properties = properties;
	}

	public String getScheduleURL() {
		return scheduleURL;
	}

	public void setScheduleURL(String scheduleURL) {
		this.scheduleURL = scheduleURL;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public Map<String, String> getPropertyKeyMap() {
		return null;
	}

}
