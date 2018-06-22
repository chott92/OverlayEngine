package de.chott.overlayengine.rest;

import de.chott.overlayengine.model.database.RunInformation;
import de.chott.overlayengine.service.database.RunInformationService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/runInformation")
public class RunInformationRestService {

	private final RunInformationService informationService;

	@Autowired
	public RunInformationRestService(RunInformationService informationService) {
		this.informationService = informationService;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RunInformation> getAll() {
		return informationService.findAll();
	}

}
