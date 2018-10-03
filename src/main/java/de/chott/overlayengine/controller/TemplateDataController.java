package de.chott.overlayengine.controller;

import de.chott.overlayengine.model.database.RunInformation;
import de.chott.overlayengine.model.template.TemplateData;
import de.chott.overlayengine.service.RunDataServiceMockup;
import de.chott.overlayengine.service.config.ConfigService;
import de.chott.overlayengine.service.database.RunInformationService;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemplateDataController {

	private final TemplateData currentData;
	private final RunInformationService runInformationService;

	@Autowired
	public TemplateDataController(RunDataServiceMockup mockup, RunInformationService runInformationService,
			ConfigService configService) {
		currentData = new TemplateData();
		currentData.setCurrentRun(mockup.getNextInformation());

		this.runInformationService = runInformationService;
	}

	public TemplateData getCurrentData() {
		return currentData;
	}

	public void setCurrentRun(RunInformation information) {
		currentData.setCurrentRun(information);

		List<RunInformation> followingRuns
				= runInformationService.findRunsAfterOrderIndex(information.getOrderIndex());

		if (followingRuns.size() > 0) {
			currentData.setNextRun(followingRuns.get(0));
		}
		if (followingRuns.size() > 1) {
			currentData.setAfterNextRun(followingRuns.get(1));
		}

		Logger.getLogger(this.getClass().getName()).info("Changed the run to " + information.getGame());
	}

	public void swapRaceRunnerNames() {
		currentData.swapRunnerNames();
	}

}
