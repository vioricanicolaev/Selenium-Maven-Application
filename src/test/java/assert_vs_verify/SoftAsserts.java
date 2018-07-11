package assert_vs_verify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {
	
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void test1() {
		int j = 100;
		int i = 200;
		
		System.out.println("First Assertion");
		softAssert.assertEquals(i, j, "I and J are not equal");
		
		System.out.println("Second Asserions");
		softAssert.assertNotEquals(i, j);
		
		System.out.println("Third Assertion");
		softAssert.assertTrue(i < j);
		
		softAssert.assertAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
