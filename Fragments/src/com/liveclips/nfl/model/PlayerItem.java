package com.liveclips.nfl.model;

public class PlayerItem {

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getplayerDetails() {
		return playerDetails;
	}

	public void setplayerDetails(String playerDetails) {
		this.playerDetails = playerDetails;
	}

	public int getPlayerImage() {
		return playerImage;
	}

	public void setPlayerImage(int playerImage) {
		this.playerImage = playerImage;
	}

	public String getPlayerdata1() {
		return playerdata1;
	}

	public void setPlayerdata1(String playerdata1) {
		this.playerdata1 = playerdata1;
	}

	public String getPlayerdata2() {
		return playerdata2;
	}

	public void setPlayerdata2(String playerdata2) {
		this.playerdata2 = playerdata2;
	}

	public String getPlayerdata3() {
		return playerdata3;
	}

	public void setPlayerdata3(String playerdata3) {
		this.playerdata3 = playerdata3;
	}

	public String getPlayerdata4() {
		return playerdata4;
	}

	public void setPlayerdata4(String playerdata4) {
		this.playerdata4 = playerdata4;
	}

	public String playerName;
	public String playerDetails;
	public int playerImage;
	public String playerdata1;
	public String playerdata2;
	public String playerdata3;
	public String playerdata4;

	public PlayerItem() {

	}

	public PlayerItem(String playerName, String playerDetails, int playerImage,
			String playerdata1, String playerdata2, String playerdata3,
			String playerdata4) {
		super();
		this.playerName = playerName;
		this.playerDetails = playerDetails;
		this.playerImage = playerImage;
		this.playerdata1 = playerdata1;
		this.playerdata2 = playerdata2;
		this.playerdata3 = playerdata3;
		this.playerdata4 = playerdata4;
	}

}
