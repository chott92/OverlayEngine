/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.chott.overlayengine.service.horaro;

public class HoraroImportServiceTest {

	public static void main(String[] args) {
		String jsonUrl = "https://horaro.org/dokomi2018/schedule.json";

		new HoraroImportService().loadDataFromHoraro(jsonUrl);
	}
}
