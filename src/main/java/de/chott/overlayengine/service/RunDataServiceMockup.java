package de.chott.overlayengine.service;

import de.chott.overlayengine.model.database.RunInformation;
import de.chott.overlayengine.service.database.RunInformationService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RunDataServiceMockup {

	private List<RunInformation> runData;
	private Iterator<RunInformation> iterator;

	private RunInformationService runInformationService;

	@Autowired
	public RunDataServiceMockup(RunInformationService runInformationService) {
		this.runInformationService = runInformationService;
		runData = runInformationService.findAll();

		if (runData == null || runData.isEmpty()) {
			runData = new ArrayList<>();

			runData.add(runInformationService
					.save(new RunInformation("Donkey Kong Country Returns", "Any%", "Wii", "michael_1985", "michael_1985x", "1:35:00", 0)));
			runData.add(runInformationService
					.save(new RunInformation("Donkey Kong Country: Tropical Freeze", "Any% WiiU", "WiiU", "Mr.Tiger", "mrtiger92", "2:00:00", 0)));
			runData.add(runInformationService
					.save(new RunInformation("Cornerstone: The Song of Tyrim", "Any% No Credits Skip", "PC", "Multiwinner", "Multiwinner", "28:00", 0)));
			runData.add(runInformationService
					.save(new RunInformation("Donkey Kong Country Returns", "100%", "Wii", "Mr.Tiger", "mrtiger92", "2:40:00", 0)));
			runData.add(runInformationService
					.save(new RunInformation("Xenoblade Chronicles", "Any% NG", "WiiU", "docmob", "docmob", "5:30:00", 0)));
			runData.add(runInformationService.save(new RunInformation("140", "Any%", "PC", "Zet", "Zet237", "20:00", 0)));
		}
		iterator = runData.iterator();
	}

	public RunInformation getNextInformation() {
		if (!iterator.hasNext()) {
			iterator = runData.iterator();
		}
		return iterator.next();
	}

}
