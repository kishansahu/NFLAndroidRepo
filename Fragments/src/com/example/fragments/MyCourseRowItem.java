package com.example.fragments;

public class MyCourseRowItem {

	
	private String courseId;
	private String courseTitle;
	private String totalLesson;
	private String totalCompleteLesson;
	private String moduleAverage;
	private String startDate;
	private String endDate;
	private String progressBarStatus;
	public MyCourseRowItem(String courseId, String courseTitle,
			String totalLesson, String totalCompleteLesson,
			String moduleAverage, String startDate, String endDate,
			String progressBarStatus) {
		super();
		this.courseId = courseId;
		this.courseTitle = courseTitle;
		this.totalLesson = totalLesson;
		this.totalCompleteLesson = totalCompleteLesson;
		this.moduleAverage = moduleAverage;
		this.startDate = startDate;
		this.endDate = endDate;
		this.progressBarStatus = progressBarStatus;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getTotalLesson() {
		return totalLesson;
	}
	public void setTotalLesson(String totalLesson) {
		this.totalLesson = totalLesson;
	}
	public String getTotalCompleteLesson() {
		return totalCompleteLesson;
	}
	public void setTotalCompleteLesson(String totalCompleteLesson) {
		this.totalCompleteLesson = totalCompleteLesson;
	}
	public String getModuleAverage() {
		return moduleAverage;
	}
	public void setModuleAverage(String moduleAverage) {
		this.moduleAverage = moduleAverage;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getProgressBarStatus() {
		return progressBarStatus;
	}
	public void setProgressBarStatus(String progressBarStatus) {
		this.progressBarStatus = progressBarStatus;
	}
	
}
