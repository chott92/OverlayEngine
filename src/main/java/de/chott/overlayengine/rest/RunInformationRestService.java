package de.chott.overlayengine.rest;

import de.chott.overlayengine.model.database.RunInformation;
import de.chott.overlayengine.service.database.RunInformationService;
import de.chott.overlayengine.service.horaro.HoraroImportConfig;
import de.chott.overlayengine.service.horaro.HoraroImportService;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/runInformation")
public class RunInformationRestService {

	private final RunInformationService informationService;
	private final HoraroImportService horaroImportService;

	@Autowired
	public RunInformationRestService(RunInformationService informationService,
			HoraroImportService horaroImportService) {
		this.informationService = informationService;
		this.horaroImportService = horaroImportService;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RunInformation> getAll() {
		return informationService.findAll();
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RunInformation getById(@PathParam("id") long id) {
		return informationService.findById(id);
	}

	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RunInformation> deleteById(@PathParam("id") long id) {
		informationService.deleteById(id);
		return getAll();
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RunInformation save(RunInformation runInformation) {
		return informationService.save(runInformation);
	}

	@POST
	@Path("/horaroImport")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RunInformation> importHoraroSchedule(HoraroImportConfig importConfig) {
		List<RunInformation> dataFromHoraro = horaroImportService.loadDataFromHoraro(importConfig);

		informationService.findAll().stream()
				.mapToLong(RunInformation::getId)
				.forEach(informationService::deleteById);

		return dataFromHoraro.stream()
				.map(informationService::save)
				.collect(Collectors.toList());
	}

}
