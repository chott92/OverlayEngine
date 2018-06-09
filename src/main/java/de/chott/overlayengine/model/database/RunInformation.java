package de.chott.overlayengine.model.database;

public class RunInformation {

    private String game;
    private String category;
    private String platform;
    private String runnerName;
    private String runnerTwitchName;
    private String estimate;

    public RunInformation() {
    }

    public RunInformation(String game, String category, String platform, String runnerName,
            String runnerTwitchName, String estimate) {
        this.game = game;
        this.category = category;
        this.platform = platform;
        this.runnerName = runnerName;
        this.runnerTwitchName = runnerTwitchName;
        this.estimate = estimate;
    }

    public String getGame() {
        return game;
    }

    public String getCategory() {
        return category;
    }

    public String getPlatform() {
        return platform;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public String getRunnerTwitchName() {
        return runnerTwitchName;
    }

    public String getEstimate() {
        return estimate;
    }
    
    
    
}
