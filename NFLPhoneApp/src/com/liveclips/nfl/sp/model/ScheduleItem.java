package com.liveclips.nfl.sp.model;

public class ScheduleItem {

	private String teamName;
	private int teamLogo;
	private String teamStatus;
	private String weekText;
	private String versusText;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public ScheduleItem(String teamName, int teamLogo, String teamStatus,
			String weekText, String versusText) {
		super();
		this.teamName = teamName;
		this.teamLogo = teamLogo;
		this.teamStatus = teamStatus;
		this.weekText = weekText;
		this.versusText = versusText;
	}

	public int getTeamLogo() {
		return teamLogo;
	}

	public void setTeamLogo(int teamLogo) {
		this.teamLogo = teamLogo;
	}

	public String getTeamStatus() {
		return teamStatus;
	}

	public void setTeamStatus(String teamStatus) {
		this.teamStatus = teamStatus;
	}

	public String getWeekText() {
		return weekText;
	}

	public void setWeekText(String weekText) {
		this.weekText = weekText;
	}

	public String getVersusText() {
		return versusText;
	}

	public void setVersusText(String versusText) {
		this.versusText = versusText;
	}

}
