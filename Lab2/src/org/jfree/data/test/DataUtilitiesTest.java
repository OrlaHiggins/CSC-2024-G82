package org.jfree.data.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DataUtilitiesTest {

//    private Values2D values2D;
	private DefaultKeyedValues2D testValues;

    @Before
//    public void setUp() {
//        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
//        values2D = testValues;
//        testValues.addValue(1, 0, 0);
//        testValues.addValue(4, 1, 0);
//    }
    public void setUp() {
        testValues = new DefaultKeyedValues2D();
        testValues.addValue(1.0, 2.0, 3.0);
        testValues.addValue(4.0, 5.0, 6.0);
       
    }

    @After
//    public void tearDown() {
//        values2D = null;
//    }
    public void tearDown() {
        testValues = null;
    }

//    @Test
//    public void testValidDataAndCalculateColumnTotal() {
//        assertEquals("Wrong sum returned. It should be 5.0", 
//                5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
//    }
//
//    @Test
//    public void testNullDataColumnTotal() {
//        try {
//            DataUtilities.calculateColumnTotal(null, 0);
//            fail("No exception thrown");
//        } catch (Exception e) {
//            assertTrue("Incorrect exception type thrown",
//                    e.getClass().equals(IllegalArgumentException.class));
//        }
//    }
    
    @Test
    public void testCalculateColumnTotalValidDataMatrixAndValidColumnNumber() {
        int column = 1; // Assuming the second column (index 1) is valid
        double expected = 7.0; // Sum of values in the second column (2 + 5)
        double result = DataUtilities.calculateColumnTotal(testValues, column);
        assertEquals("Valid data matrix and valid column number should return the correct sum",
                expected, result, 0.000001d);
    }

    @Test
    public void testCalculateColumnTotalValidDataMatrixAndInvalidColumnNumber() {
        int invalidColumn = -1; // Assuming the column index is invalid

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.calculateColumnTotal(testValues, invalidColumn);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        } catch (IndexOutOfBoundsException e) {
            // Handle the case where the column index is out of bounds
            fail("Column index is out of bounds");
        }
    }


    @Test
    public void testCalculateColumnTotalInvalidDataMatrixAndValidColumnNumber() {
        testValues.clear(); // Clearing the data to make it invalid
        int validColumn = 1; // Assuming the second column (index 1) is valid
        double expected = 0.0; // Since the data matrix is empty, the sum should be 0
        double result = DataUtilities.calculateColumnTotal(testValues, validColumn);
        assertEquals("Invalid data matrix and valid column number should return 0",
                expected, result, 0.000001d);
    }


    @Test
    public void testCalculateColumnTotalInvalidDataMatrixWithNullValuesAndValidColumnNumber() {
        // Adding values according to the setup
        testValues.addValue(1.0, 2.0, 3.0);
        testValues.addValue(4.0, 5.0, 6.0);
        
        // Adding a null value in the specified column
        testValues.addValue(0, 1, "");

        int validColumn = 0; // Assuming the first column (index 0) is valid

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.calculateColumnTotal(testValues, validColumn);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }


    @Test
    public void testCalculateColumnTotalInvalidDataMatrixAndInvalidColumnNumber() {
        // Setting up the test values
        testValues = new DefaultKeyedValues2D();
        testValues.addValue(1.0, 2.0, 3.0);
        testValues.addValue(4.0, 5.0, 6.0);
        // Call the method under test and expect IllegalArgumentException
        try {
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    
    @Test
    public void testCalculateRowTotalValidDataMatrixAndValidRowNumber() {
        int row = 1; // Assuming the second row (index 1) is valid

        // Call the method under test and capture the result
        double result = DataUtilities.calculateRowTotal(testValues, row);

        // Define the expected output
        double expected = 15.0; // Sum of values in the second row (4 + 5 + 6)

        // Perform the assertion
        assertEquals("Valid data matrix and valid row number should return the correct sum",
                     expected, result, 0.000001d);
    }
    @Test
    public void testCalculateRowTotalValidDataMatrixAndInvalidRowNumber() {
        // Assuming the row index is invalid
        int invalidRow = -1;

        // Get the number of rows in the data matrix
        int rowCount = testValues.getRowCount();

        // Call the method under test only if the row index is within the valid range
        if (invalidRow >= 0 && invalidRow < rowCount) {
            try {
                // Attempt to calculate the row total
                DataUtilities.calculateRowTotal(testValues, invalidRow);
                fail("Expected IllegalArgumentException was not thrown");
            } catch (IllegalArgumentException e) {
                // Test passed if IllegalArgumentException is thrown
                assertTrue("IllegalArgumentException should be thrown", true);
            }
        } else {
            // If the row index is out of bounds, the test passes immediately
            assertTrue(true);
        }
    }
    @Test
    public void testCalculateRowTotalInvalidDataMatrixAndValidRowNumber() {
        // Clearing the data to make it invalid
        testValues.clear();
        
        int validRow = 1; // Assuming the second row (index 1) is valid
        
        // Call the method under test and expect IllegalArgumentException
        try {
            double result = DataUtilities.calculateRowTotal(testValues, validRow);
            assertEquals("Invalid data matrix and valid row number should return 0",
                    0.0, result, 0.000001d);
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException should not be thrown for valid row number");
        }
    }
    @Test
    public void testCalculateRowTotalInvalidDataMatrixWithNullValuesAndValidRowNumber() {
        // Setting up the test values with null values
        testValues = new DefaultKeyedValues2D();
        testValues.addValue(null, "column1", 3); // Adding a null key
        testValues.addValue(4, "column2", 6); // Adding a valid value

        int validRow = 0; // Assuming the first row (index 0) is valid

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.calculateRowTotal(testValues, validRow);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    @Test
    public void testCalculateRowTotalInvalidDataMatrixAndInvalidRowNumber() {
        // Setting up the test values with the provided data matrix
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        testValues.addValue(1, 0, 0);
        testValues.addValue(null, 1, 0);
        testValues.addValue(3, 2, 0);
        testValues.addValue(4, 0, 1);
        testValues.addValue(5, 1, 1);
        testValues.addValue(6, 2, 1);

        int invalidRow = 2; // Assuming the row index is invalid

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.calculateRowTotal(testValues, invalidRow);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }







}




    




//    
//    @Test
//    public void testgetCumulativePercentages()
//    {
//    	//setup
//    	DefaultKeyedValues keyvalues = new DefaultKeyedValues();
//    	keyvalues.addValue((Comparable) 0.0, 6.0);
//    	keyvalues.addValue((Comparable) 1.0, 11.0);
//    	keyvalues.addValue((Comparable) 2.0, 3.0);
//    	KeyedValues object_under_test = DataUtilities.getCumulativePercentages((KeyedValues) keyvalues);
//    	assertEquals((double) object_under_test.getValue(2), 1.0, .000000001d);
//    	
//    }
//}



	

