package de.chott.overlayengine.controller;

import de.chott.overlayengine.model.database.RunInformation;
import de.chott.overlayengine.model.template.TemplateData;
import de.chott.overlayengine.service.RunDataServiceMockup;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemplateDataController {

	private final TemplateData currentData;

	@Autowired
	public TemplateDataController(RunDataServiceMockup mockup) {
		currentData = new TemplateData();
		currentData.setCurrentRun(mockup.getNextInformation());
	}

	public TemplateData getCurrentData() {
		return currentData;
	}

	public void setCurrentRun(RunInformation information) {
		currentData.setCurrentRun(information);
		Logger.getLogger(this.getClass().getName()).info("Changed the run to " + information.getGame());
	}
}
