package de.chott.overlayengine.service.schedule;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import de.chott.overlayengine.controller.TemplateDataController;
import de.chott.overlayengine.service.config.ConfigService;
import org.jboss.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DonationTrackerSchedulerService {

	private final TemplateDataController dataController;
	private final ConfigService configService;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public DonationTrackerSchedulerService(TemplateDataController dataController,
			ConfigService configService) {
		this.dataController = dataController;
		this.configService = configService;
	}

	@Scheduled(fixedRate = 60000)
	public void updateTrackerDataFeed() {
		String incentiveUrl = "http://" + configService.getConfig("incentive_feed_url");
		String incentiveFeed = loadDataFromUrl(incentiveUrl);
		if (incentiveFeed != null) {
			dataController.getCurrentData().setIncentiveFeed(incentiveFeed);
		}

		String donationFeedUrl = "http://" + configService.getConfig("donation_feed_url");
		String donationFeed = loadDataFromUrl(donationFeedUrl);
		if (donationFeed != null) {
			dataController.getCurrentData().setDonationFeed(donationFeed);
		}
	}

	private String loadDataFromUrl(String targetUrl) {
		ClientConfig clientConfig = new DefaultClientConfig();
		ClientResponse response = Client.create(clientConfig)
				.resource(targetUrl)
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			logger.info("Could not load data from URL " + targetUrl + ", Response was Status Code " + response.getStatus());
			return null;
		}

		return response.getEntity(String.class);
	}
}
