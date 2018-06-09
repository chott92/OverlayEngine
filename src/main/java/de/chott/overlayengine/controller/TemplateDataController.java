
package de.chott.overlayengine.controller;

import de.chott.overlayengine.model.database.RunInformation;
import de.chott.overlayengine.model.template.TemplateData;

public class TemplateDataController {
    
    private TemplateData currentData;

    public TemplateDataController() {
        currentData = new TemplateData();
    }

    public TemplateData getCurrentData() {
        return currentData;
    } 
    
    public void setCurrentRun(RunInformation information){
        currentData.setCurrentRun(information);
    }
}
