package com.TopBloc.CodingChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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
		avgScore = avgScore/students.size();
		return avgScore;
	}
	//Will make String array into Json String array representation.
	public static String JsonifyStringArray(String [] arr) {
		String jsonified = "[";
		for(String s : arr) {
			s = "\"" + s + "\",";
			jsonified += s;
		}
		//Removes last comma
		jsonified = jsonified.substring(0, jsonified.length()-1);
		jsonified += "]";
		return jsonified;
	}
	//Use stream to sort them alphabetically
	public static String[] SortStringArrAlpha(String[] ids) {
		String[] sortedStudentIds = Stream.of(ids).sorted().toArray(String[]::new);
		return sortedStudentIds;
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
		try {
			URL url = new URL ("http://54.90.99.192:5000/challenge");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			
			String jsonInputString = "{\"id\": \"jbroncato@luc.edu\","
					+ "\"name\": \"Jacob(Preferred name Jack) Broncato\","
					+ "\"average\": " + avg + ","
					+ "\"studentIds\": " + JsonifyStringArray(ids) + "}";

			System.out.println(jsonInputString);
			
			try(OutputStream os = con.getOutputStream()) {
			    byte[] input = jsonInputString.getBytes("utf-8");
			    os.write(input, 0, input.length);			
			}
			
			try(BufferedReader br = new BufferedReader(
					  new InputStreamReader(con.getInputStream(), "utf-8"))) {
					    StringBuilder response = new StringBuilder();
					    String responseLine = null;
					    while ((responseLine = br.readLine()) != null) {
					        response.append(responseLine.trim());
					    }
					    System.out.println(response.toString());
					}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		InMemoryDB db = new InMemoryDB();
		db.createDB();
		HashMap<String, Student> allStudentsHashMap = db.getAllStudents();
		int avgScore = getAverageScore(allStudentsHashMap);
		String[] studentIds = getCompSciWomen(allStudentsHashMap);
		studentIds = SortStringArrAlpha(studentIds);
		System.out.println("Average Score: " + avgScore);
		System.out.println("Women in Computer Science: ");
		for(String s : studentIds) {
			System.out.println(s);
		}
		sendResults(avgScore, studentIds);
	}
}
