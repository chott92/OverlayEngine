package de.chott.overlayengine.rest;

import de.chott.overlayengine.controller.TemplateDataController;
import de.chott.overlayengine.model.database.RunInformation;
import de.chott.overlayengine.service.database.RunInformationService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/dashboard")
public class DashboardRestService {

	private final TemplateDataController templateDataController;
	private final RunInformationService runInformationService;

	@Autowired
	public DashboardRestService(TemplateDataController templateDataController,
			RunInformationService runInformationService) {
		this.templateDataController = templateDataController;
		this.runInformationService = runInformationService;
	}

	@GET
	@Path("/currentActiveRun")
	@Produces(MediaType.APPLICATION_JSON)
	public RunInformation getCurrentActiveRunInformation() {
		return templateDataController.getCurrentData().getCurrentRun();
	}

	@POST
	@Path("/currentActiveRun/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RunInformation setCurrentActiveRunInformation(@PathParam("id") String idString) {
		Long id = Long.valueOf(idString);
		RunInformation newCurrentRunInformation = runInformationService.findById(id);

		templateDataController.setCurrentRun(newCurrentRunInformation);

		return templateDataController.getCurrentData().getCurrentRun();
	}

	@POST
	@Path("/swapRaceRunnerNames")
	public void swapRaceRunnerNames() {
		templateDataController.swapRaceRunnerNames();
	}
}
