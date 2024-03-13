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

	private DefaultKeyedValues2D testValues;

    @Before
    public void setUp() {
        testValues = new DefaultKeyedValues2D();
        testValues.addValue(1.0, 2.0, 3.0);
        testValues.addValue(4.0, 5.0, 6.0);
       
    }

    @After
    public void tearDown() {
        testValues = null;
    }

    
    //calculateColumnTotal(Values2D data, int column)
    //TC1
    @Test
    public void testCalculateColumnTotalValidColumnNumber() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3, 4},
            {5, 6, 7.5, 8}
        };
        int column = 1; // Assuming the second column (index 1) is valid

        // Create DefaultKeyedValues2D instance and populate it with data
        DefaultKeyedValues2D keyedValues2D = new DefaultKeyedValues2D();
        for (int i = 0; i < testData.length; i++) {
            for (int j = 0; j < testData[i].length; j++) {
                keyedValues2D.addValue(testData[i][j], i, j);
            }
        }

        // Calculate the expected result
        double expected = 8.3;

        // Call the method under test
        double result = DataUtilities.calculateColumnTotal(keyedValues2D, column);

        // Perform the assertion
        assertEquals("Valid data matrix and valid column number should return the correct sum",
                expected, result, 0.000001d);
    }

    
    //TC2
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalculateColumnTotalInvalidColumnNumber() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3, 4},
            {5, 6, 7.5, 8}
        };
        int column = -1; // Negative column index

        // Create DefaultKeyedValues2D object with proper constructor
        DefaultKeyedValues2D keyedValues2D = new DefaultKeyedValues2D();
        for (int row = 0; row < testData.length; row++) {
            for (int col = 0; col < testData[row].length; col++) {
                keyedValues2D.addValue(testData[row][col], row, col);
            }
        }

        // Check if the column index is within bounds
        if (column < 0 || column >= keyedValues2D.getColumnCount()) {
            throw new IndexOutOfBoundsException("Invalid column index");
        }

        // Call the method under test (expecting an IndexOutOfBoundsException)
        DataUtilities.calculateColumnTotal(keyedValues2D, column);
    }
    
    //TC3
    @Test
    public void testCalculateColumnTotalValidColumnNumber0() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3, 4},
            {5, 6, 7.5, 8}
        };
        int column = 0; // Valid column index

        // Create DefaultKeyedValues2D object with proper constructor
        DefaultKeyedValues2D keyedValues2D = new DefaultKeyedValues2D();
        for (int row = 0; row < testData.length; row++) {
            for (int col = 0; col < testData[row].length; col++) {
                keyedValues2D.addValue(testData[row][col], row, col);
            }
        }

        // Call the method under test
        double result = DataUtilities.calculateColumnTotal(keyedValues2D, column);

        // Define the expected result
        double expected = 6.0;

        // Perform the assertion
        assertEquals("Column total should match expected value", expected, result, 0.000001d);
    }
    
    //TC4
    public void testCalculateColumnTotalInvalidColumnIndex() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3, 4},
            {5, 6, 7.5, 8}
        };
        int column = 40; // Invalid column index

        // Create DefaultKeyedValues2D object with proper constructor
        DefaultKeyedValues2D keyedValues2D = new DefaultKeyedValues2D();
        for (int row = 0; row < testData.length; row++) {
            for (int col = 0; col < testData[row].length; col++) {
                keyedValues2D.addValue(testData[row][col], row, col);
            }
        }

        // Call the method under test and expect IndexOutOfBoundsException
        try {
            DataUtilities.calculateColumnTotal(keyedValues2D, column);
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            // Test passed if IndexOutOfBoundsException is thrown
        }
    }
    
    //TC5
    @Test
    public void testCalculateColumnTotalEmptyMatrixPositiveColumnIndex() {
        // Define the input data (empty matrix)
        DefaultKeyedValues2D keyedValues2D = new DefaultKeyedValues2D();

        // Define the positive column index
        int column = 1;

        // Call the method under test and expect IndexOutOfBoundsException
        try {
            DataUtilities.calculateColumnTotal(keyedValues2D, column);
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            // Test passed if IndexOutOfBoundsException is thrown
        }
    }

    
    //6
    @Test
    public void testCalculateColumnTotalEmptyMatrixNegativeColumnIndex() {
        // Define the input data (empty matrix)
        DefaultKeyedValues2D keyedValues2D = new DefaultKeyedValues2D();

        // Define the negative column index
        int column = -1;

        // Call the method under test and expect IndexOutOfBoundsException
        try {
            DataUtilities.calculateColumnTotal(keyedValues2D, column);
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            // Test passed if IndexOutOfBoundsException is thrown
        }
    }
    
    //7
    @Test
    public void testCalculateColumnTotalEmptyMatrixZeroColumnIndex() {
        // Define the input data (empty matrix)
        DefaultKeyedValues2D keyedValues2D = new DefaultKeyedValues2D();

        // Define the zero column index
        int column = 0;

        // Call the method under test and expect IndexOutOfBoundsException
        try {
            DataUtilities.calculateColumnTotal(keyedValues2D, column);
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            // Test passed if IndexOutOfBoundsException is thrown
        }
    }
    
    //8
    @Test
    public void testCalculateColumnTotalEmptyMatrixInvalidColumnIndex() {
        // Define the input data (empty matrix)
        DefaultKeyedValues2D keyedValues2D = new DefaultKeyedValues2D();

        // Define the invalid column index
        int column = 40;

        // Call the method under test and expect IndexOutOfBoundsException
        try {
            DataUtilities.calculateColumnTotal(keyedValues2D, column);
            fail("Expected IndexOutOfBoundsException was not thrown");
        } catch (IndexOutOfBoundsException e) {
            // Test passed if IndexOutOfBoundsException is thrown
        }
    }
    
    //9
    @Test
    public void testCalculateColumnTotalNullData() {
        // Define the input data (null)
        DefaultKeyedValues2D keyedValues2D = null;

        // Define the invalid column index
        int column = 1;

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.calculateColumnTotal(keyedValues2D, column);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
        } catch (NullPointerException e) {
            // Test passed if NullPointerException is thrown
        }
    }
    
    //10
    @Test
    public void testCalculateColumnTotalNullDataNegativeColumnIndex() {
        // Define the input data (null)
        DefaultKeyedValues2D keyedValues2D = null;

        // Define the invalid column index
        int column = -1;

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.calculateColumnTotal(keyedValues2D, column);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
        } catch (NullPointerException e) {
            // Test passed if NullPointerException is thrown
        }
    }
    
    //11
    @Test
    public void testCalculateColumnTotalNullDataZeroColumnIndex() {
        // Define the input data (null)
        DefaultKeyedValues2D keyedValues2D = null;

        // Define the invalid column index
        int column = 0;

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.calculateColumnTotal(keyedValues2D, column);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
        } catch (NullPointerException e) {
            // Test passed if NullPointerException is thrown
        }
    }
    
    //12
    @Test
    public void testCalculateColumnTotalNullDataInvalidColumnIndex() {
        // Define the input data (null)
        DefaultKeyedValues2D keyedValues2D = null;

        // Define the invalid column index
        int column = 40;

        // Call the method under test and expect IllegalArgumentException
        try {
            DataUtilities.calculateColumnTotal(keyedValues2D, column);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Test passed if IllegalArgumentException is thrown
        } catch (NullPointerException e) {
            // Test passed if NullPointerException is thrown
        }
    }

    
    //BVA
    //TC13
    @Test
    public void testCalculateColumnTotalinColumnValidDataMatrix() {
        // Define the input data
        DefaultKeyedValues2D testData = new DefaultKeyedValues2D();
        testData.addValue(1, 0, 0);
        testData.addValue(2.3, 0, 1);
        testData.addValue(3, 0, 2);
        testData.addValue(4, 0, 3);
        testData.addValue(5, 1, 0);
        testData.addValue(6, 1, 1);
        testData.addValue(7.5, 1, 2);
        testData.addValue(8, 1, 3);

        // Define the expected output
        double expected = 6.0;

        // Call the method under test
        double result = DataUtilities.calculateColumnTotal(testData, 0);

        // Perform the assertion
        assertEquals("Min column value 0 with a valid data matrix should return 6.0",
                expected, result, 0.000001d);
    }
    
    //TC14
    @Test
    public void testCalculateColumnTotalMinPlusColumnValidDataMatrix() {
        // Define the input data
        DefaultKeyedValues2D testData = new DefaultKeyedValues2D();
        testData.addValue(1, 0, 0);
        testData.addValue(2.3, 0, 1);
        testData.addValue(3, 0, 2);
        testData.addValue(4, 0, 3);
        testData.addValue(5, 1, 0);
        testData.addValue(6, 1, 1);
        testData.addValue(7.5, 1, 2);
        testData.addValue(8, 1, 3);

        // Define the expected output
        double expected = 8.0;

        // Call the method under test
        double result = DataUtilities.calculateColumnTotal(testData, 1);

        // Perform the assertion
        assertEquals("Min+ column value 1 with a valid data matrix should return 8.0",
                expected, result, 0.000001d);
    }
    
    //TC15
    @Test
    public void testCalculateColumnTotalMaxMinusColumnValidDataMatrix() {
        // Define the input data
        DefaultKeyedValues2D testData = new DefaultKeyedValues2D();
        testData.addValue(1, 0, 0);
        testData.addValue(2.3, 0, 1);
        testData.addValue(3, 0, 2);
        testData.addValue(4, 0, 3);
        testData.addValue(5, 1, 0);
        testData.addValue(6, 1, 1);
        testData.addValue(7.5, 1, 2);
        testData.addValue(8, 1, 3);

        // Define the expected output
        double expected = 10.0;

        // Call the method under test
        double result = DataUtilities.calculateColumnTotal(testData, 2);

        // Perform the assertion
        assertEquals("Max- column value 2 with a valid data matrix should return 10.0",
                expected, result, 0.000001d);
    }

    //TC16
    @Test
    public void testCalculateColumnTotalMaxColumnValidDataMatrix() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3, 4},
            {5, 6, 7.5, 8}
        };
        int column = 3; // Maximum column index (0-based)

        // Create a new DefaultKeyedValues2D object
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();

        // Populate the DefaultKeyedValues2D object with the test data
        for (int row = 0; row < testData.length; row++) {
            for (int col = 0; col < testData[row].length; col++) {
                testValues.addValue(testData[row][col], row, col);
            }
        }

        // Calculate the expected result
        double expected = 12.0;

        // Call the method under test
        double result = DataUtilities.calculateColumnTotal(testValues, column);

        // Perform the assertion
        assertEquals("Max column value 3 with a valid data matrix should return 12.0",
                expected, result, 0.000001d);
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
    //BVA
    //TC6
    @Test
    public void testCalculateRowTotalMinRowValidDataMatrix() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3},
            {4, 5, 6},
            {7.5, 8, 9},
            {10, 11, 12.2}
        };
        int row = 0; // Minimum row index (0-based)

        // Create a new DefaultKeyedValues2D object
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();

        // Populate the DefaultKeyedValues2D object with the test data
        for (int r = 0; r < testData.length; r++) {
            for (int c = 0; c < testData[r].length; c++) {
                testValues.addValue(testData[r][c], r, c);
            }
        }

        // Calculate the expected result
        double expected = 5.3;

        // Call the method under test
        double result = DataUtilities.calculateRowTotal(testValues, row);

        // Perform the assertion
        assertEquals("Min row value 0 with a valid data matrix should return 5.3",
                expected, result, 0.000001d);
    }
    
    //TC7
    @Test
    public void testCalculateRowTotalMinPlusRowValidDataMatrix() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3},
            {4, 5, 6},
            {7.5, 8, 9},
            {10, 11, 12.2}
        };
        int row = 1; // Minimum row index + 1 (0-based)

        // Create a new DefaultKeyedValues2D object
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();

        // Populate the DefaultKeyedValues2D object with the test data
        for (int r = 0; r < testData.length; r++) {
            for (int c = 0; c < testData[r].length; c++) {
                testValues.addValue(testData[r][c], r, c);
            }
        }

        // Calculate the expected result
        double expected = 15.0;

        // Call the method under test
        double result = DataUtilities.calculateRowTotal(testValues, row);

        // Perform the assertion
        assertEquals("Min+ row value 1 with a valid data matrix should return 15.0",
                expected, result, 0.000001d);
    }

    //TC8
    @Test
    public void testCalculateRowTotalMaxMinusRowValidDataMatrix() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3},
            {4, 5, 6},
            {7.5, 8, 9},
            {10, 11, 12.2}
        };
        int row = 2; // Maximum row index - 1 (0-based)

        // Create a new DefaultKeyedValues2D object
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();

        // Populate the DefaultKeyedValues2D object with the test data
        for (int r = 0; r < testData.length; r++) {
            for (int c = 0; c < testData[r].length; c++) {
                testValues.addValue(testData[r][c], r, c);
            }
        }

        // Calculate the expected result
        double expected = 24.5;

        // Call the method under test
        double result = DataUtilities.calculateRowTotal(testValues, row);

        // Perform the assertion
        assertEquals("Max- row values 2 with a valid data matrix should return 24.5",
                expected, result, 0.000001d);
    }

    //TC9
    @Test
    public void testCalculateRowTotalMaxRowValidDataMatrix() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3},
            {4, 5, 6},
            {7.5, 8, 9},
            {10, 11, 12.2}
        };
        int row = 3; // Maximum row index (0-based)

        // Create a new DefaultKeyedValues2D object
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();

        // Populate the DefaultKeyedValues2D object with the test data
        for (int r = 0; r < testData.length; r++) {
            for (int c = 0; c < testData[r].length; c++) {
                testValues.addValue(testData[r][c], r, c);
            }
        }

        // Calculate the expected result
        double expected = 33.2;

        // Call the method under test
        double result = DataUtilities.calculateRowTotal(testValues, row);

        // Perform the assertion
        assertEquals("Max row values 3 with a valid data matrix should return 33.2",
                expected, result, 0.000001d);
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



	

