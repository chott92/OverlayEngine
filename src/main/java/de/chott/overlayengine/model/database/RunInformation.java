package de.chott.overlayengine.model.database;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = RunInformation.FIND_ALL, query = "SELECT ri FROM RunInformation ri")
})
public class RunInformation extends AbstractEntity {

	public static final String FIND_ALL = "RunInformation.findAll";

	private static final long serialVersionUID = 54861326468523L;

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

	public void setGame(String game) {
		this.game = game;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setRunnerName(String runnerName) {
		this.runnerName = runnerName;
	}

	public void setRunnerTwitchName(String runnerTwitchName) {
		this.runnerTwitchName = runnerTwitchName;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + Objects.hashCode(this.game);
		hash = 89 * hash + Objects.hashCode(this.category);
		hash = 89 * hash + Objects.hashCode(this.platform);
		hash = 89 * hash + Objects.hashCode(this.runnerName);
		hash = 89 * hash + Objects.hashCode(this.runnerTwitchName);
		hash = 89 * hash + Objects.hashCode(this.estimate);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RunInformation other = (RunInformation) obj;
		if (!Objects.equals(this.game, other.game)) {
			return false;
		}
		if (!Objects.equals(this.category, other.category)) {
			return false;
		}
		if (!Objects.equals(this.platform, other.platform)) {
			return false;
		}
		if (!Objects.equals(this.runnerName, other.runnerName)) {
			return false;
		}
		if (!Objects.equals(this.runnerTwitchName, other.runnerTwitchName)) {
			return false;
		}
		if (!Objects.equals(this.estimate, other.estimate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RunInformation{" + "game=" + game + ", category=" + category + ", platform=" + platform
				+ ", runnerName=" + runnerName + ", runnerTwitchName="
				+ runnerTwitchName + ", estimate=" + estimate + '}';
	}

}
