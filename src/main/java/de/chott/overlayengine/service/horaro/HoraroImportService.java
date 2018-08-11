package de.chott.overlayengine.service.horaro;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.json.JSONObject;

public class HoraroImportService {

	public void loadDataFromHoraro(String jsonUrl) {
		String jsonData = getJsonData(jsonUrl);
		JSONObject jsonObject = new JSONObject(jsonData);

		System.out.println(jsonData);
	}

	private String getJsonData(String jsonUrl) {
		ClientConfig clientConfig = new DefaultClientConfig();
		return Client.create(clientConfig)
				.resource(jsonUrl)
				.get(ClientResponse.class)
				.getEntity(String.class);

	}
}
