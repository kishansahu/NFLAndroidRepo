package com.liveclips.nfl.sp.model;

public class DriveItem {

	public String teamShortName;
	public String driveEvent;
	public String driveDetail;
	public int teamLogo;

	public DriveItem(String teamShortName, String driveEvent,
			String driveDetail, int teamLogo) {
		super();
		this.teamShortName = teamShortName;
		this.driveEvent = driveEvent;
		this.driveDetail = driveDetail;
		this.teamLogo = teamLogo;
	}

}
