package org.jfree.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;

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
    public void testCalculateColumnTotalValidColumnNumberZero() {
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
    @Test
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
        double expected = 8.3;

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
        double expected = 10.5;

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

    //Lab3
    @Test
    public void testCalculateColumnTotalWithNullValues() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3, 4},
            {5, 6, 7.5, 8}
        };
        int column = 2; // Assuming the third column (index 2) is valid

        // Create DefaultKeyedValues2D instance and populate it with data
        DefaultKeyedValues2D keyedValues2D = new DefaultKeyedValues2D();
        for (int i = 0; i < testData.length; i++) {
            for (int j = 0; j < testData[i].length; j++) {
                keyedValues2D.addValue(testData[i][j], i, j);
            }
        }

        // Set a value at a specific row and column to null
        keyedValues2D.setValue(null, 1, column);

        // Calculate the expected result
        double expected = 3.0; // Sum of non-null values in the third column

        // Call the method under test
        double result = DataUtilities.calculateColumnTotal(keyedValues2D, column);

        // Perform the assertion
        assertEquals("Valid data matrix with some null values should return the correct sum",
                expected, result, 0.000001d);
    }


    
    //calculateRowTotal(Values2D data, int row)
    //TC1
    @Test
    public void testCalculateRowTotalValidRowIndex() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3},
            {4, 5, 6},
            {7.5, 8, 9},
            {10, 11, 12.2}
        };
        
        // Create a new DefaultKeyedValues2D instance
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();

        // Populate the DefaultKeyedValues2D instance with testData
        for (int i = 0; i < testData.length; i++) {
            for (int j = 0; j < testData[i].length; j++) {
                data.addValue(testData[i][j], i, j);
            }
        }
        
        // Define the valid row index
        int row = 1;

        // Calculate the expected result
        double expected = 15.0;

        // Call the method under test
        double result = DataUtilities.calculateRowTotal(data, row);

        // Perform the assertion
        assertEquals("Sum of values for row index 1 should be 15.0", expected, result, 0.000001d);
    }
    
    //TC2
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalculateRowTotalNegativeRowIndex() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3},
            {4, 5, 6},
            {7.5, 8, 9},
            {10, 11, 12.2}
        };

        // Create a new DefaultKeyedValues2D instance
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();

        // Populate the DefaultKeyedValues2D instance with testData
        for (int i = 0; i < testData.length; i++) {
            for (int j = 0; j < testData[i].length; j++) {
                data.addValue(testData[i][j], i, j);
            }
        }

        // Define the invalid row index
        int row = -1;

        // Call the method under test (should throw IndexOutOfBoundsException)
        double result = DataUtilities.calculateRowTotal(data, row);
    }
    
    //TC3
    @Test
    public void testCalculateRowTotalValidRowIndexZero() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3},
            {4, 5, 6},
            {7.5, 8, 9},
            {10, 11, 12.2}
        };

        // Create a new DefaultKeyedValues2D instance
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();

        // Populate the DefaultKeyedValues2D instance with testData
        for (int i = 0; i < testData.length; i++) {
            for (int j = 0; j < testData[i].length; j++) {
                data.addValue(testData[i][j], i, j);
            }
        }

        // Define the valid row index
        int row = 0;

        // Calculate the expected result manually
        double expected = 0.0;
        for (double value : testData[row]) {
            expected += value;
        }

        // Call the method under test
        double result = DataUtilities.calculateRowTotal(data, row);

        // Assert that the result matches the expected value
        assertEquals(expected, result, 0.0001);
    }
    
    //TC4
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCalculateRowTotalInvalidRowIndex() {
        // Define the input data
        double[][] testData = {
            {1, 2.3, 3},
            {4, 5, 6},
            {7.5, 8, 9},
            {10, 11, 12.2}
        };

        // Create a new DefaultKeyedValues2D instance
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();

        // Populate the DefaultKeyedValues2D instance with testData
        for (int i = 0; i < testData.length; i++) {
            for (int j = 0; j < testData[i].length; j++) {
                data.addValue(testData[i][j], i, j);
            }
        }

        // Define the invalid row index
        int row = 40; // or any other out-of-bounds index

        // Call the method under test (should throw IndexOutOfBoundsException)
        double result = DataUtilities.calculateRowTotal(data, row);
    }
    
    //TC5
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalEmptyMatrixPositiveRowIndex() {
        // Create an empty DefaultKeyedValues2D instance
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();

        // Define the positive row index
        int row = 1;

        // Call the method under test (should throw IllegalArgumentException)
        double result = DataUtilities.calculateRowTotal(data, row);
    }
    
    //TC6
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalEmptyMatrixNegativeRowIndex() {
        // Create an empty DefaultKeyedValues2D instance
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();

        // Define the negative row index
        int row = -1;

        // Call the method under test (should throw IllegalArgumentException)
        double result = DataUtilities.calculateRowTotal(data, row);
    }
    
    //TC7
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalEmptyMatrixZeroRowIndex() {
        // Create an empty DefaultKeyedValues2D instance
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();

        // Define the zero row index
        int row = 0;

        // Call the method under test (should throw IllegalArgumentException)
        double result = DataUtilities.calculateRowTotal(data, row);
    }
    
    //TC8
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalEmptyMatrixInvalidRowIndex() {
        // Create an empty DefaultKeyedValues2D instance
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();

        // Define the invalid row index
        int row = 40;

        // Call the method under test (should throw IllegalArgumentException)
        double result = DataUtilities.calculateRowTotal(data, row);
    }
    
    //TC9
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalNullDataPositiveRowIndex() {
        try {
            // Define the null input data
            DefaultKeyedValues2D data = null;

            // Define the positive row index
            int row = 1;

            // Call the method under test (should throw IllegalArgumentException)
            double result = DataUtilities.calculateRowTotal(data, row);
        } catch (NullPointerException e) {
            // NullPointerException occurred, rethrowing as IllegalArgumentException
            throw new IllegalArgumentException("Unexpected NullPointerException");
        }
    }

    //TC10
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalNullDataNegativeRowIndex() {
        try {
            // Define the null input data
            DefaultKeyedValues2D data = null;

            // Define the negative row index
            int row = -1;

            // Call the method under test (should throw IllegalArgumentException)
            double result = DataUtilities.calculateRowTotal(data, row);
        } catch (NullPointerException e) {
            // NullPointerException occurred, rethrowing as IllegalArgumentException
            throw new IllegalArgumentException("Unexpected NullPointerException");
        }
    }
    
    //TC11
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalNullDataZeroRowIndex() {
        try {
            // Define the input data as null
            Values2D data = null;

            // Define the row index as zero
            int row = 0;

            // Call the method under test
            double result = DataUtilities.calculateRowTotal(data, row);
        } catch (NullPointerException e) {
            // Expected behavior, rethrow as IllegalArgumentException
            throw new IllegalArgumentException("data must not be null");
        }
    }
    
    //TC12

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalNullDataInvalidRowIndex() {
        try {
            // Define the input data as null
            Values2D data = null;

            // Define an invalid row index
            int row = 40;

            // Call the method under test
            double result = DataUtilities.calculateRowTotal(data, row);
        } catch (NullPointerException e) {
            // Expected behavior, rethrow as IllegalArgumentException
            throw new IllegalArgumentException("data must not be null");
        }
    }
    
    //BVA
    //TC13
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
        double expected = 6.3;

        // Call the method under test
        double result = DataUtilities.calculateRowTotal(testValues, row);

        // Perform the assertion
        assertEquals("Min row value 0 with a valid data matrix should return 6.3",
                expected, result, 0.000001d);
    }
    
    //TC14
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

    //TC15
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

    //TC16
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

    //Lab3
    
    @Test
    public void testCalculateRowTotalAllNullValues() {
        // Define the input data with all null values
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(null, 0, 0);
        data.addValue(null, 0, 1);
        data.addValue(null, 0, 2);

        // Define the row index
        int row = 0;

        // Call the method under test
        double result = DataUtilities.calculateRowTotal(data, row);

        // Define the expected output
        double expected = 0.0;

        // Perform the assertion
        assertEquals("Row total should be 0.0 when all values are null",
                expected, result, 0.0);
    }

    
    //createNumberArray(double[] data)
    //TC1
    @Test
    public void testCreateNumberArray() {
        // Define the input data
        double[] inputData = {1.0, 2.5, 3.7, 4.2};
        
        // Define the expected output
        Number[] expectedOutput = {1.0, 2.5, 3.7, 4.2};
        
        // Call the method under test
        Number[] result = DataUtilities.createNumberArray(inputData);
        
        // Assert that the result matches the expected output
        assertArrayEquals(expectedOutput, result);
    }
    
    //TC2

    @Test
    public void testCreateNumberArrayWithNaN() {
        // Define the input data with NaN value
        double[] invalidInputData = {Double.NaN};
        
        // Call the method under test and assert that it throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray(invalidInputData);
        });
    }
    
    //TC3
    @Test
    public void testCreateNumberArrayWithNullArray() {
        // Define the input data as null
        double[] invalidInputData = null;
        
        // Call the method under test and assert that it throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray(invalidInputData);
        });
    }
    
    //TC4
    @Test
    public void testCreateNumberArrayWithEmptyArray() {
        // Define the input data as an empty array
        double[] emptyInputData = {};
        
        // Call the method under test
        Number[] result = DataUtilities.createNumberArray(emptyInputData);
        
        // Verify that the result is an empty array of Type Number
        assertArrayEquals(new Number[] {}, result);
    }
    
    //createNumberArray2D(double[][] data)
    
    //TC1
    @Test
    public void testCreateNumberArray2DValidData() {
            // Define the input data
            double[][] inputData = {{1.0, 2.5}, {3.7, 4.2}};
            
            // Define the expected output data
            Number[][] expectedOutput = {{1.0, 2.5}, {3.7, 4.2}};
            
            // Call the method under test
            Number[][] result = DataUtilities.createNumberArray2D(inputData);
            
            // Assert that the result matches the expected output
            assertArrayEquals(expectedOutput, result);
        }
    
    //TC2
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DInvalidData() {
        // Define the input data
        double[][] invalidData = {{Double.NaN, Double.NaN}, {Double.NaN, Double.NaN}};
        
        // Call the method under test (should throw IllegalArgumentException)
        DataUtilities.createNumberArray2D(invalidData);
    }
    
    //TC3
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DNullData() {
        // Call the method under test with null input (should throw IllegalArgumentException)
        DataUtilities.createNumberArray2D(null);
    }
    
    //TC4
    @Test
    public void testCreateNumberArrayEmptyArray() {
        // Define the input data as an empty array of type double
        double[] data = {};

        // Call the method under test
        Number[] result = DataUtilities.createNumberArray(data);

        // Verify that the result is an empty array of type Number
        assertTrue(Arrays.equals(result, new Number[0]));
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
    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentagesEmptyKeyedValues() {
        // Define an empty KeyedValues object
        KeyedValues keyValues = new DefaultKeyedValues();

        // Call the method under test (should throw IllegalArgumentException)
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
    }
    
    //TC3
    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentagesNullInput() {
        // Define a null KeyedValues object
        KeyedValues keyValues = null;

        // Call the method under test (should throw IllegalArgumentException)
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
    }
    
    //TC4
    @Test
    public void testGetCumulativePercentagesValidKeyedValuesOneEntry() {
        // Define the input data
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue((Comparable<Integer>) 0, 10.0); // Specify the type explicitly

        // Call the method under test
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);

        // Define the expected output
        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue((Comparable<Integer>) 0, 1.0); // Specify the type explicitly

        // Perform the assertion
        assertEquals("Cumulative percentages should match the expected values",
                expected.getValue(0), result.getValue(0));
    }

    //TC5
    @Test
    public void testGetCumulativePercentagesValidKeyedValuesNegativeValues() {
        // Define the input data
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue((Comparable<?>)0, -2.0);
        keyValues.addValue((Comparable<?>)1, -6.0);
        keyValues.addValue((Comparable<?>)2, -3.0);

        // Call the method under test
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);

        // Define the expected output
        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue((Comparable<?>)0, 0.5);
        expected.addValue((Comparable<?>)1, 0.25);
        expected.addValue((Comparable<?>)2, 0.375);

        // Perform the assertion
        for (int i = 0; i < expected.getItemCount(); i++) {
            assertEquals("Cumulative percentage should match the expected value",
                    expected.getValue(i), result.getValue(i));
        }
    }
    
    //Lab3
    
    // Add this test case to cover the branch where the Number value retrieved from the KeyedValues object is null
    @Test
    public void testGetCumulativePercentagesWithNullValues() {
        // Define the input data with null values
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue("A", 10.0);
        keyValues.addValue("B", null);
        keyValues.addValue("C", 30.0);

        // Call the method under test
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);

        // Define the expected output
        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue("A", 25.0); // (10.0 / (10.0 + 30.0)) * 100
        expected.addValue("B", 25.0); // (0 / (10.0 + 30.0)) * 100
        expected.addValue("C", 50.0); // (30.0 / (10.0 + 30.0)) * 100

        // Perform the assertion
        assertEquals("Cumulative percentages should match the expected values",
                expected, result);
    }

    // Add this test case to cover the branch where all Number values retrieved from the KeyedValues object are null
    @Test
    public void testGetCumulativePercentagesWithAllNullValues() {
        // Define the input data with all null values
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue("A", null);
        keyValues.addValue("B", null);
        keyValues.addValue("C", null);

        // Call the method under test
        KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);

        // Define the expected output
        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue("A", 33.33333333333333); // (0 / (0 + 0 + 0)) * 100
        expected.addValue("B", 66.66666666666666); // (0 / (0 + 0 + 0)) * 100
        expected.addValue("C", 100.0); // (0 / (0 + 0 + 0)) * 100

        // Perform the assertion
        assertEquals("Cumulative percentages should match the expected values",
                expected, result);
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



	

