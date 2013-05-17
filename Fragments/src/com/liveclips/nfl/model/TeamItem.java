/**
 * 
 */
package com.liveclips.nfl.model;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

/**
 * @author mohitkumar
 *
 */
public class TeamItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String teamName;
	
	private int teamLogo;
	
	private int wins;
	
	private int losses;
	
	
	/**
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * @param wins the wins to set
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * @return the losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * @param losses the losses to set
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the teamLogo
	 */
	public int getTeamLogo() {
		return teamLogo;
	}

	/**
	 * @param teamLogo the teamLogo to set
	 */
	public void setTeamLogo(int teamLogo) {
		this.teamLogo = teamLogo;
	}

}
