package com.TopBloc.CodingChallenge.Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.TopBloc.CodingChallenge.Domain.Student;
//TODO DRY on the read in
public class InMemoryDB {
	
	private HashMap<String, Student> studentDB = new HashMap<String, Student>();
	
	public static void main(String[] args) {
		
	}
	
	public InMemoryDB() {
		
	}
	
	public void createDB() {
		try {
		this.initStudents();
		this.readScores("Test Scores.xlsx");
		this.readScores("Test Retake Scores.xlsx");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, Student> getAllStudents(){
		return this.studentDB;
	}
	
	public Student getStudent(String studentId){
		if(studentDB.containsKey(studentId)) {
			return studentDB.get(studentId);
		}else {
			throw new Error("No student with that ID");
		}
	}
	
	//Grabs all students from file and initializes Hashmap(database) with them
    private void initStudents() throws IOException {
    		
            //File file = new File("./resources/Student Info.xlsx"); // creating a new file instance
            //FileInputStream inputStream = new FileInputStream(file);
            InputStream inputStream = getClass().getResourceAsStream("/Student Info.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();
            //skipping titles of Columns
            iterator.next();
            
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                List<Object> rowContent = new ArrayList<Object>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                     
                    switch (cell.getCellType()) {
                        case STRING:
                            rowContent.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                        	rowContent.add(cell.getNumericCellValue());
                            break;
					default:
						break;
                    }
                }
                String studentID = Double.toString((Double)rowContent.get(0)).substring(0,5);
                String major = (String)rowContent.get(1);
                Student.Gender gender;
                //find out gender
                switch(((String) rowContent.get(2)).toLowerCase()) {
                case "f":
                	gender = Student.Gender.F;
                	break;
                case "m":
                	gender = Student.Gender.M;
                	break;
                default:
                	gender = Student.Gender.O;
                	break;
                }
                Student newStudent = new Student(studentID,major,gender);
                studentDB.put(studentID, newStudent);
            }
             
            workbook.close();
            inputStream.close();
        }
    // Reads scores onto the corresponding students when given score file
    private void readScores(String filename) throws IOException {
		
        //File file = new File(filename); // creating a new file instance
        //FileInputStream inputStream = new FileInputStream(file);
        InputStream inputStream = getClass().getResourceAsStream("/"+filename);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        //skipping titles of Columns
        iterator.next();
        
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            // h
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            List<Object> rowContent = new ArrayList<Object>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                 
                switch (cell.getCellType()) {
                    case STRING:
                        rowContent.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                    	rowContent.add(cell.getNumericCellValue());
                        break;
				default:
					break;
                }
            }
            // h
            String studentID = Double.toString((Double)rowContent.get(0)).substring(0,5);
            int score = (int)Math.round((Double)rowContent.get(1));
            studentDB.get(studentID).addScore(score);
        }
         
        workbook.close();
        inputStream.close();
    }
    
    }
