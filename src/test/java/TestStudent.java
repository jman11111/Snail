
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.TopBloc.CodingChallenge.Domain.Student;
 
public class TestStudent {
	
	Student testStudent = new Student("111111", "Computer Science", Student.Gender.F, 95);
	
	@Test
	@DisplayName("ID Test")
    void TestStudentName() {
		Assertions.assertEquals("111111", testStudent.getStudentId());
    }
	
	@Test
	@DisplayName("Major Test")
    void TestStudentMajor() {
		Assertions.assertEquals("Computer Science", testStudent.getMajor());
    }
	
	@Test
	@DisplayName("Gender Test")
    void TestStudentGender() {
		Assertions.assertEquals(Student.Gender.F, testStudent.getGender());
    }
	
	@Test
	@DisplayName("HighScore Test")
    void TestStudentHS() {
		testStudent.addScore(76);
		testStudent.addScore(98);
		Assertions.assertEquals(98, testStudent.getHighestScore());
    }
}
