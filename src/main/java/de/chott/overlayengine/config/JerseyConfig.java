/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.chott.overlayengine.config;

import de.chott.overlayengine.rest.DashboardRestService;
import de.chott.overlayengine.rest.RunInformationRestService;
import de.chott.overlayengine.rest.TestRunChangeRestService;
import de.chott.overlayengine.rest.template.TemplateDataRestService;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(DashboardRestService.class);
		register(TemplateDataRestService.class);
		register(TestRunChangeRestService.class);
		register(RunInformationRestService.class);
	}

}
