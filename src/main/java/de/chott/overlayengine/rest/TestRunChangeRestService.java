package de.chott.overlayengine.rest;

import de.chott.overlayengine.controller.TemplateDataController;
import de.chott.overlayengine.service.RunDataServiceMockup;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/changeRun")
public class TestRunChangeRestService {

	private final RunDataServiceMockup dataServiceMockup;
	private final TemplateDataController dataController;

	@Autowired
	public TestRunChangeRestService(RunDataServiceMockup dataServiceMockup, TemplateDataController dataController) {
		this.dataServiceMockup = dataServiceMockup;
		this.dataController = dataController;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response changeGame() {
		dataController.setCurrentRun(dataServiceMockup.getNextInformation());
		return Response.ok().build();
	}
}
