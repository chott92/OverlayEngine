package de.chott.overlayengine.service.horaro;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import de.chott.overlayengine.model.database.RunInformation;
import static de.chott.overlayengine.service.horaro.HoraroImportUtils.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

public class HoraroImportService {

	public void loadDataFromHoraro(String jsonUrl, Map<String, String> valueKeyMap) {
		String jsonData = getJsonData(jsonUrl);
		JSONObject jsonObject = new JSONObject(jsonData);

		Map<String, Integer> columnOrderMap = getColumnOrderMap(jsonObject, valueKeyMap);

		List<RunInformation> newRunInformations = getNewRunInformations(jsonObject, columnOrderMap);

		System.out.println(jsonData);
	}

	private String getJsonData(String jsonUrl) {
		ClientConfig clientConfig = new DefaultClientConfig();
		return Client.create(clientConfig)
				.resource(jsonUrl)
				.get(ClientResponse.class)
				.getEntity(String.class);

	}

	private Map<String, Integer> getColumnOrderMap(JSONObject jsonObject, Map<String, String> valueKeyMap) {
		Map<String, Integer> columnOrderMap = new HashMap<>();
		List<String> columns = new ArrayList<>();

		JSONArray columnArray = jsonObject.getJSONObject("schedule").getJSONArray("columns");
		for (int i = 0; i < columnArray.length(); i++) {
			columns.add(columnArray.getString(i));
		}

		Optional.ofNullable(valueKeyMap.get(GAME_KEY)).ifPresent(val -> {
			columnOrderMap.put(GAME_KEY, columns.indexOf(val));
		});
		Optional.ofNullable(valueKeyMap.get(RUNNER_NAME_KEY)).ifPresent(val -> {
			columnOrderMap.put(RUNNER_NAME_KEY, columns.indexOf(val));
		});
		Optional.ofNullable(valueKeyMap.get(CATEGORY_KEY)).ifPresent(val -> {
			columnOrderMap.put(CATEGORY_KEY, columns.indexOf(val));
		});
		Optional.ofNullable(valueKeyMap.get(ESTIMATE_KEY)).ifPresent(val -> {
			columnOrderMap.put(ESTIMATE_KEY, columns.indexOf(val));
		});
		Optional.ofNullable(valueKeyMap.get(PLATFORM_KEY)).ifPresent(val -> {
			columnOrderMap.put(PLATFORM_KEY, columns.indexOf(val));
		});

		return columnOrderMap;
	}

	private List<RunInformation> getNewRunInformations(JSONObject jsonObject, Map<String, Integer> columnOrderMap) {
		List<HoraroImportElement> importElements = getInputElements(jsonObject, columnOrderMap);

		final List<HoraroImportElement> sortedElements = importElements.stream()
				.sorted()
				.collect(Collectors.toList());

		return sortedElements.stream().map(element -> element.toRunInformation(sortedElements.indexOf(element)))
				.collect(Collectors.toList());
	}

	private List<HoraroImportElement> getInputElements(JSONObject jsonObject, Map<String, Integer> columnOrderMap) {
		JSONArray jsonArray = jsonObject.getJSONObject("schedule").getJSONArray("items");
		List<HoraroImportElement> elements = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			elements.add(new HoraroImportElement(object, columnOrderMap));
		}

		return elements;
	}
}
