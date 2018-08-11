/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.chott.overlayengine.service.horaro;

import java.util.HashMap;
import java.util.Map;

public class HoraroImportServiceTest {

	public static void main(String[] args) {
		String jsonUrl = "https://horaro.org/dokomi2018/schedule.json";
//		String jsonUrl = "https://horaro.org/sgdq2018/schedule.json";

		new HoraroImportService().loadDataFromHoraro(jsonUrl, getValueKeyMap());
	}

	private static Map<String, String> getValueKeyMap() {
		Map<String, String> valueKeyMap = new HashMap<>();

		valueKeyMap.put(HoraroImportUtils.GAME_KEY, "Game");
		valueKeyMap.put(HoraroImportUtils.CATEGORY_KEY, "Category");
		valueKeyMap.put(HoraroImportUtils.RUNNER_NAME_KEY, "Runner");
		valueKeyMap.put(HoraroImportUtils.PLATFORM_KEY, "Platform");
//		valueKeyMap.put(HoraroImportUtils.GAME_KEY, "Spiel");
//		valueKeyMap.put(HoraroImportUtils.CATEGORY_KEY, "Kategorie");
//		valueKeyMap.put(HoraroImportUtils.RUNNER_NAME_KEY, "Runner");
//		valueKeyMap.put(HoraroImportUtils.PLATFORM_KEY, "Streamhost");
//		valueKeyMap.put(HoraroImportUtils.ESTIMATE_KEY, "Estimate");

		return valueKeyMap;
	}
}
