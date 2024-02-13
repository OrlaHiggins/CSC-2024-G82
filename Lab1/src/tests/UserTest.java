package tests;

import model.User;
import utils.TestUtils;

public class UserTest {

	public static void testUserConstructor() {
		String test_username = "mike";
		String test_password = "my_passwd";
		String test_first_name = "Mike";
		String test_last_name = "Smith";
		String test_mobile_number = "07771234567";
		
		User testUser = new User(test_username, test_password, test_first_name, test_last_name, test_mobile_number);
		
		System.out.println("Starting the assertions of the test method: testUserConstructor");
		
		boolean passed = true;
		
		if(testUser.getUsername() == test_username)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getUsername-Passed" + TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getUsername-Failed" + TestUtils.TEXT_COLOR_RESET);
		if(testUser.getPassword() == test_password)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getPassword-Passed" + TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getPassword-Failed" + TestUtils.TEXT_COLOR_RESET);
		
		//TODO1
		if(testUser.getFirst_name() == test_first_name)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getFirst_name-Passed" + TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getFirst_name-Failed" + TestUtils.TEXT_COLOR_RESET);
		if(testUser.getLast_name() == test_last_name)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getLast_name-Passed" + TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getLast_name-Failed" + TestUtils.TEXT_COLOR_RESET);
		if(testUser.getMobile_number() == test_mobile_number)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC5-getMobile_number-Passed" + TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TC5-getMobile_number-Failed" + TestUtils.TEXT_COLOR_RESET);
		
		
		if (passed)
			System.out.println("All TC's passed.");
		
		if (testUser.getUsername() != test_username) {
			System.out.println("TC3 Failed: first_name did not match");
			passed = false;
		}
		if (testUser.getPassword() != test_password) {
			System.out.println("TC3 Failed: first_name did not match");
			passed = false;
		}
		if (testUser.getFirst_name() != test_first_name) {
			System.out.println("TC3 Failed: first_name did not match");
			passed = false;
		}
		if (testUser.getLast_name() != test_last_name) {
			System.out.println("TC4 Failed: last_name did not match");
			passed = false;
		}
		if (testUser.getMobile_number() != test_mobile_number) {
			System.out.println("TC5 Failed: mobile_number did not match");
			passed = false;
		}
		
		//TODO3
		//using asserts
		assert testUser.getUsername() == test_username;
		//assert 1==2;
		
		//TODO4
		assert testUser.getPassword() == test_password;
		assert testUser.getFirst_name() == test_first_name;
		assert testUser.getLast_name() == test_last_name;
		assert testUser.getMobile_number() == test_mobile_number;
		
		System.out.println("All Java assertions in the test suite passed (none failed).");
		
	}
	
	 public static void main(String[] args) {
	        testUserConstructor();

}
}
