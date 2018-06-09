package de.chott.overlayengine.model.template;

import de.chott.overlayengine.model.database.RunInformation;

public class TemplateData {
    private RunInformation currentRun;
    private RunInformation nextRun;
    private RunInformation afterNextRun;

    public TemplateData() {
    }

    public RunInformation getCurrentRun() {
        return currentRun;
    }

    public void setCurrentRun(RunInformation currentRun) {
        this.currentRun = currentRun;
    }

    public RunInformation getNextRun() {
        return nextRun;
    }

    public void setNextRun(RunInformation nextRun) {
        this.nextRun = nextRun;
    }

    public RunInformation getAfterNextRun() {
        return afterNextRun;
    }

    public void setAfterNextRun(RunInformation afterNextRun) {
        this.afterNextRun = afterNextRun;
    }

    
}
