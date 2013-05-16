package com.liveclips.nfl.model;

public class DriveItem {

	public String teamShortName;
	public String driveEvent;
	public String driveYardsCovered;
	public String driveTime;
	public int teamLogo;

	public DriveItem(String teamShortName, String driveEvent,
			 int teamLogo, String driveTime, String driveYardsCovered) {
		super();
		this.teamShortName = teamShortName;
		this.driveEvent = driveEvent;
		this.driveTime = driveTime;
		this.driveYardsCovered = driveYardsCovered;
		this.teamLogo = teamLogo;
	}

}
