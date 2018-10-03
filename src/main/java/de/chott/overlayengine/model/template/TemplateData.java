package de.chott.overlayengine.model.template;

import de.chott.overlayengine.model.database.RunInformation;

public class TemplateData {

	private RunInformation currentRun;
	private RunInformation nextRun;
	private RunInformation afterNextRun;
	private boolean runnerNamesSwapped;
	private String incentiveFeed;
	private String donationFeed;

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

	public boolean isRunnerNamesSwapped() {
		return runnerNamesSwapped;
	}

	public void swapRunnerNames() {
		runnerNamesSwapped = !runnerNamesSwapped;
	}

	public String getIncentiveFeed() {
		return incentiveFeed;
	}

	public void setIncentiveFeed(String incentiveFeed) {
		this.incentiveFeed = incentiveFeed;
	}

	public String getDonationFeed() {
		return donationFeed;
	}

	public void setDonationFeed(String donationFeed) {
		this.donationFeed = donationFeed;
	}

}
