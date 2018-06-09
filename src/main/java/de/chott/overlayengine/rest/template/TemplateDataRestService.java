/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.chott.overlayengine.rest.template;

import de.chott.overlayengine.controller.TemplateDataController;
import de.chott.overlayengine.model.template.TemplateData;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/templateData")
public class TemplateDataRestService {

    private final TemplateDataController dataController;

    @Autowired
    public TemplateDataRestService(TemplateDataController dataController) {
        this.dataController = dataController;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TemplateData getTemplateData(){
        return dataController.getCurrentData();
    }
    
}
