package com.TopBloc.CodingChallenge;

import java.util.HashMap;
import java.util.Map;

import com.TopBloc.CodingChallenge.Database.InMemoryDB;
import com.TopBloc.CodingChallenge.Domain.Student;

public class ChallengeMain {
	
	public static int getAverageScore(HashMap<String, Student> students) {
		int avgScore = 0;
		for (Map.Entry mapElement : students.entrySet()) {  
            //get students highest score
            int score = (((Student)mapElement.getValue()).getHighestScore()); 
            avgScore += score;
        } 
		avgScore = avgScore/students.size();
		return avgScore;
	}
	
	public static void main(String[] args) {
		InMemoryDB db = new InMemoryDB();
		db.createDB();
		HashMap<String, Student> allStudentsHashMap = db.getAllStudents();
		System.out.println(getAverageScore(allStudentsHashMap));
	}
}
