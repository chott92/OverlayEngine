package de.chott.overlayengine.controller;

import de.chott.overlayengine.model.database.RunInformation;
import de.chott.overlayengine.model.template.TemplateData;
import org.springframework.stereotype.Component;

@Component
public class TemplateDataController {

	private final TemplateData currentData;

	public TemplateDataController() {
		currentData = new TemplateData();
	}

	public TemplateData getCurrentData() {
		return currentData;
	}

	public void setCurrentRun(RunInformation information) {
		currentData.setCurrentRun(information);
	}
}
