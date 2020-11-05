package com.TopBloc.CodingChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.TopBloc.CodingChallenge.Database.InMemoryDB;
import com.TopBloc.CodingChallenge.Domain.Student;

public class ChallengeMain {
	
	//Gets average Score of all students
	public static int getAverageScore(HashMap<String, Student> students) {
		int avgScore = 0;
		for (Map.Entry mapElement : students.entrySet()) {  
            //get students highest score
            int score = (((Student)mapElement.getValue()).getHighestScore()); 
            avgScore += score;
        } 
		System.out.println(avgScore);
		avgScore = avgScore/students.size();
		return avgScore;
	}
	
	//Gets an Array of the String ids of all Female Computer Science Majors
	public static String[] getCompSciWomen(HashMap<String, Student> students) {
		ArrayList<String> studentIdStrings = new ArrayList<String>();
		for (Map.Entry mapElement : students.entrySet()) {  
			Student s = ((Student)mapElement.getValue());
            //get students with Gender of Female and Majoring in Computer Science
            if(s.getGender() == Student.Gender.F && s.getMajor().toLowerCase().equals("computer science")) {
            	studentIdStrings.add(s.getStudentId());
            }
        } 
		//convert to array
		String[] Ids = new String[studentIdStrings.size()];
		Ids = studentIdStrings.toArray(Ids);
		return Ids;
	}
	
	public static void sendResults(int avg, String[] ids) {
		
	}
	
	public static void main(String[] args) {
		InMemoryDB db = new InMemoryDB();
		db.createDB();
		HashMap<String, Student> allStudentsHashMap = db.getAllStudents();
		System.out.println(getAverageScore(allStudentsHashMap));
		String[] studentIds = getCompSciWomen(allStudentsHashMap);
		for(String s : studentIds) {
			System.out.println(s);
		}
	}
}
