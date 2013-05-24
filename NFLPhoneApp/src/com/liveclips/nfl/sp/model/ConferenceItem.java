/**
 * 
 */
package com.liveclips.nfl.sp.model;

import java.io.Serializable;


/**
 * @author mohitkumar
 *
 */
public class ConferenceItem implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String conferenceName;
	
	private TeamItem team_1;
	
	private TeamItem team_2;
	
	private TeamItem team_3;
	
	private TeamItem team_4;

	/**
	 * @return the conferenceName
	 */
	public String getConferenceName() {
		return conferenceName;
	}

	/**
	 * @param conferenceName the conferenceName to set
	 */
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	/**
	 * @return the team_1
	 */
	public TeamItem getTeam_1() {
		return team_1;
	}

	/**
	 * @param team_1 the team_1 to set
	 */
	public void setTeam_1(TeamItem team_1) {
		this.team_1 = team_1;
	}

	/**
	 * @return the team_2
	 */
	public TeamItem getTeam_2() {
		return team_2;
	}

	/**
	 * @param team_2 the team_2 to set
	 */
	public void setTeam_2(TeamItem team_2) {
		this.team_2 = team_2;
	}

	/**
	 * @return the team_3
	 */
	public TeamItem getTeam_3() {
		return team_3;
	}

	/**
	 * @param team_3 the team_3 to set
	 */
	public void setTeam_3(TeamItem team_3) {
		this.team_3 = team_3;
	}

	/**
	 * @return the team_4
	 */
	public TeamItem getTeam_4() {
		return team_4;
	}

	/**
	 * @param team_4 the team_4 to set
	 */
	public void setTeam_4(TeamItem team_4) {
		this.team_4 = team_4;
	}
	

}
