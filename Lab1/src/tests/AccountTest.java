package tests;

import model.Account;
import utils.TestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountTest {

    public static void testConstructor() {
        String test_account_number = "12345678";
        String test_username_of_account_holder = "mike";
        String test_account_type = "Savings";
        String test_account_opening_date = "09/02/2024";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date testDate = dateFormat.parse(test_account_opening_date);

            Account testAccount = new Account(test_account_number, test_username_of_account_holder,
                    test_account_type, testDate);

            System.out.println("Starting the assertions of the test method: testConstructor");

            boolean passed = true; // Initialize the variable

            if (testAccount.getAccount_number().equals(test_account_number))
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getAccount_number-Passed" + TestUtils.TEXT_COLOR_RESET);
            else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getAccount_number-Failed" + TestUtils.TEXT_COLOR_RESET);
                passed = false;
            }

            if (testAccount.getUsername_of_account_holder().equals(test_username_of_account_holder))
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getUsername_of_account_holder-Passed" + TestUtils.TEXT_COLOR_RESET);
            else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getUsername_of_account_holder-Failed" + TestUtils.TEXT_COLOR_RESET);
                passed = false;
            }

            if (testAccount.getAccount_type().equals(test_account_type))
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getAccount_type-Passed" + TestUtils.TEXT_COLOR_RESET);
            else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getAccount_type-Failed" + TestUtils.TEXT_COLOR_RESET);
                passed = false;
            }

            if (testAccount.getAccount_opening_date().equals(testDate))
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getAccount_opening_date-Passed" + TestUtils.TEXT_COLOR_RESET);
            else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getAccount_opening_date-Failed" + TestUtils.TEXT_COLOR_RESET);
                passed = false;
            }

            if (passed)
                System.out.println("All TC's passed.");

   
            Account testAccountInitialized = new Account(test_account_number, test_username_of_account_holder,
                    test_account_type, testDate);

            if (testAccountInitialized.getAccount_number().equals(test_account_number)) {
                System.out.println("TC1 Passed: account_number matched");
            } else {
                System.out.println("TC1 Failed: account_number did not match");
                passed = false;
            }

            if (testAccountInitialized.getUsername_of_account_holder().equals(test_username_of_account_holder)) {
                System.out.println("TC2 Passed: username_of_account_holder matched");
            } else {
                System.out.println("TC2 Failed: username_of_account_holder did not match");
                passed = false;
            }

            if (testAccountInitialized.getAccount_type().equals(test_account_type)) {
                System.out.println("TC3 Passed: account_type matched");
            } else {
                System.out.println("TC3 Failed: account_type did not match");
                passed = false;
            }

            if (testAccountInitialized.getAccount_opening_date().equals(testDate)) {
                System.out.println("TC4 Passed: account_opening_date matched");
            } else {
                System.out.println("TC4 Failed: account_opening_date did not match");
                passed = false;
            }

            assert testAccount.getAccount_number().equals(test_account_number);
            assert testAccount.getUsername_of_account_holder().equals(test_username_of_account_holder);
            assert testAccount.getAccount_type().equals(test_account_type);
            assert testAccount.getAccount_opening_date().equals(testDate);

            System.out.println("All Java assertions in the test suite passed (none failed).");
		
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
	
	public static void testSetters() {
		
	}
	

	public static void main(String[] args) {
		testConstructor();
		testSetters();

	}

}
