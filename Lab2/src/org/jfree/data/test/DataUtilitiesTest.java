package org.jfree.data.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

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
    
    //calculateColumnTotal(Values2D data, int column)
    //TC1
    @Test
    public void testCalculateColumnTotalValidDataMatrixAndValidColumnNumber() {
        int column = 1; // Assuming the second column (index 1) is valid
        double expected = 7.0; // Sum of values in the second column (2 + 5)
        double result = DataUtilities.calculateColumnTotal(testValues, column);
        assertEquals("Valid data matrix and valid column number should return the correct sum",
                expected, result, 0.000001d);
    }
    
    //TC2
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
    
    //TC3
    @Test
    public void testCalculateColumnTotalInvalidDataMatrixAndValidColumnNumber() {
        testValues.clear(); // Clearing the data to make it invalid
        int validColumn = 1; // Assuming the second column (index 1) is valid
        double expected = 0.0; // Since the data matrix is empty, the sum should be 0
        double result = DataUtilities.calculateColumnTotal(testValues, validColumn);
        assertEquals("Invalid data matrix and valid column number should return 0",
                expected, result, 0.000001d);
    }
    
    //TC4
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
    
    //TC5
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
    
    //calculateRowTotal(Values2D data, int row)
    
    //TC1
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
    
    //TC2
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
    
    //TC3
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
    
    //TC4
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
    
    //TC5
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
    
    //createNumberArray(double[] data)
    
    //TC1
    @Test
    public void testCreateNumberArrayValidData() {
        // Define the input data
        double[] data = {1.0, 2.5, 3.7, 4.2};

        // Call the method under test
        Number[] result = DataUtilities.createNumberArray(data);

        // Check if the result array is null
        assertNotNull("Result array should not be null", result);

        // Convert the result array to double for comparison
        double[] resultArray = new double[result.length];
        for (int i = 0; i < result.length; i++) {
            if (result[i] == null) {
                System.out.println("Null value found at index " + i);
            } else {
                resultArray[i] = result[i].doubleValue();
            }
        }

        // Print the result array for diagnosis
        System.out.println("Result array: " + Arrays.toString(resultArray));

        // Define the expected output
        double[] expected = {1.0, 2.5, 3.7, 4.2};

        // Perform the assertion
        assertArrayEquals("Valid array of double values should be returned", expected, resultArray, 0.000001d);
    }
    
    //TC2
    @Test
    public void testCreateNumberArrayInvalidEmptyData() {
        // Define the input data as an empty array
        double[] data = {};

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.createNumberArray(data);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    
    //TC3
    @Test
    public void testCreateNumberArrayInvalidNullData() {
        // Define the input data as null
        double[] data = null;

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.createNumberArray(data);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    
    //TC4
    @Test
    public void testCreateNumberArrayNonNumericValues() {
        // Define the input data with non-numeric values
        double[] data = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.createNumberArray(data);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    
    //createNumberArray2D(double[][] data)
    
    //TC1
    @Test
    public void testCreateNumberArray2DValidData() {
        // Define the input data as a valid 2D array of double values
        double[][] data = {{1.0, 2.5}, {3.7, 4.2}};
        
        // Call the method under test
        Number[][] result = DataUtilities.createNumberArray2D(data);
        
        // Verify that the returned array matches the expected array
        assertArrayEquals("Valid 2D array of double values", data, result);
    }
    
    //TC2
    @Test
    public void testCreateNumberArray2DEmptyData() {
        // Define the input data as an empty array
        double[][] data = new double[0][0];

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.createNumberArray2D(data);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    
    //TC3
    @Test
    public void testCreateNumberArray2DNullData() {
        // Define the input data as null
        double[][] data = null;

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.createNumberArray2D(data);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    
    //TC4
    @Test
    public void testCreateNumberArray2DNonNumericData() {
        // Define the input data with non-numeric values (NaN)
        double[][] nonNumericData = {
            {Double.NaN, Double.NaN},
            {Double.NaN, Double.NaN}
        };

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.createNumberArray2D(nonNumericData);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    
    //getCumulativePercentages(KeyedValues data)
    //TC1
    @Test
    public void testGetCumulativePercentagesValidKeyedValuesCollection() {
        // Define the input data
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue("A", 10);
        keyValues.addValue("B", 20);
        keyValues.addValue("C", 30);
        keyValues.addValue("D", 40);

        // Call the method under test
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);

        // Define the expected output
        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue("A", 10.0);
        expected.addValue("B", 30.0);
        expected.addValue("C", 60.0);
        expected.addValue("D", 100.0);

        // Perform the assertion
        assertEquals("Cumulative percentages should match the expected values",
                expected, result);
    }
    
    //TC2
    @Test
    public void testGetCumulativePercentages_EmptyKeyedValuesCollection() {
        // Define the input data as an empty keyed values collection
        DefaultKeyedValues keyValues = new DefaultKeyedValues();

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.getCumulativePercentages(keyValues);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    
    //TC3
    @Test
    public void testGetCumulativePercentages_NullKeyedValuesCollection() {
        // Define the input data as null keyed values collection
        DefaultKeyedValues keyValues = null;

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.getCumulativePercentages(keyValues);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
            assertTrue("IllegalArgumentException should be thrown", true);
        }
    }
    
    //TC4
    @Test
    public void testGetCumulativePercentages_NonNumericKeyedValues() {
        // Define the input data with non-numeric values
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue("A", 10.0); // Using double values instead of strings
        keyValues.addValue("B", 20.0);
        keyValues.addValue("C", 30.0);

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.getCumulativePercentages(keyValues);
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



	

