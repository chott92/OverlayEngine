package de.chott.overlayengine.service.horaro;

import de.chott.overlayengine.model.database.RunInformation;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class HoraroImportElement implements Comparable<HoraroImportElement> {

	final LocalDateTime startTime;
	final String estimate;
	final String game;
	final String runnerName;
	final String category;
	final String platform;

	HoraroImportElement(JSONObject gameObject, Map<String, Integer> columnOrderMap) {
		startTime = LocalDateTime.parse(gameObject.getString("scheduled"), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		estimate = getEstimate(gameObject, columnOrderMap);
		game = getValueFromData(gameObject, columnOrderMap, HoraroImportUtils.GAME_KEY);
		runnerName = getValueFromData(gameObject, columnOrderMap, HoraroImportUtils.RUNNER_NAME_KEY);
		category = getValueFromData(gameObject, columnOrderMap, HoraroImportUtils.CATEGORY_KEY);
		platform = getValueFromData(gameObject, columnOrderMap, HoraroImportUtils.PLATFORM_KEY);
	}

	private String getEstimate(JSONObject gameObject, Map<String, Integer> columnOrderMap) {
		if (columnOrderMap.containsKey(HoraroImportUtils.ESTIMATE_KEY)) {
			return gameObject.getJSONArray("data")
					.getString(columnOrderMap.get(HoraroImportUtils.ESTIMATE_KEY))
					.replace("00:", "");
		} else {
			return LocalTime.ofSecondOfDay(gameObject.getInt("length_t"))
					.toString()
					.replace("00:", "");
		}
	}

	private String getValueFromData(JSONObject gameObject, Map<String, Integer> columnOrderMap, String key) {
		if (columnOrderMap.containsKey(key)) {
			try {
				return gameObject.getJSONArray("data")
						.getString(columnOrderMap.get(key));
			} catch (JSONException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public String getEstimate() {
		return estimate;
	}

	public String getGame() {
		return game;
	}

	public String getRunnerName() {
		return runnerName;
	}

	public String getCategory() {
		return category;
	}

	public String getPlatform() {
		return platform;
	}

	RunInformation toRunInformation(int orderIndex) {
		return new RunInformation(game, category, platform, runnerName, null, estimate, orderIndex);
	}

	@Override
	public int compareTo(HoraroImportElement o) {
		return startTime.compareTo(o.startTime);
	}

}
