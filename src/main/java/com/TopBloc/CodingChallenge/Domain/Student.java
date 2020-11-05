package com.TopBloc.CodingChallenge.Domain;

import java.util.ArrayList;

public class Student {
	
	private String studentId;
	private String major;
	//M is Male, F is Female, O is other
	public enum Gender{
		M,
		F,
		O
	}
	
	private Gender gender;
	private ArrayList<Integer> testScores = new ArrayList<Integer>();
	private int highestScore;
	
	public Student() {
		
	}
	//Used with info available from Student Info Sheet
	public Student(String studentId,String major,Gender gender) {
		this.studentId = studentId;
		this.major = major;
		this.gender = gender;
	}
	
	public Student(String studentId,String major,Gender gender,Integer testScore) {
		this.studentId = studentId;
		this.major = major;
		this.gender = gender;
		this.testScores.add(testScore);
		this.highestScore = testScore;
	}
	
	public int getHighestScore() {
		return this.highestScore;
	}
	
	public void addScore(int testScore) {
		this.testScores.add(testScore);
		if(testScore > this.highestScore) {
			this.highestScore = testScore;
		}
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public ArrayList<Integer> getTestScores() {
		return testScores;
	}
	public void setTestScores(ArrayList<Integer> testScores) {
		this.testScores = testScores;
	}
	public String toString() {
		return "ID: " + this.studentId + " Major: " + this.major + " HighScore: " + this.highestScore;
	}
	
	
}
