import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.TopBloc.CodingChallenge.ChallengeMain;
import com.TopBloc.CodingChallenge.Database.InMemoryDB;

public class TestChallengeMain {

	InMemoryDB db = new InMemoryDB();
	
	@BeforeEach
	void InitDB() {
		db.createDB();
	}
	
	@Test
	@DisplayName("Avg Score Test")
    void TestAverageScore() {
		Assertions.assertEquals(89,ChallengeMain.getAverageScore(db.getAllStudents()));
    }
	
	@Test
	@DisplayName("Avg Score Test")
    void TestCompSciWomen() {
		String[] realResults = {"11211","11243","43422"};
		String[] actualResults = ChallengeMain.getCompSciWomen(db.getAllStudents());
		actualResults = ChallengeMain.SortStringArrAlpha(actualResults);
		Assertions.assertArrayEquals(realResults,actualResults);
    }
	
}
