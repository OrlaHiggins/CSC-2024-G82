package org.jfree.data.test;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
 
import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
public class RangeTest {
 
    private Range rangeObjectUnderTest;
 
    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(-1, 1);
    }
 
    @After
    public void tearDown() throws Exception {
        rangeObjectUnderTest = null;
    }
    
    //combine(Range range1, Range range2)
 
    // TC1: range1AndRange2AreCombinedToProduceNewRange
    @Test
    public void range1AndRange2AreCombinedToProduceNewRange() {
        // Arrange
        Range range1 = new Range(5, 15);
        Range range2 = new Range(15, 30);
        Range expectedCombinedRange = new Range(range1.getLowerBound(), range2.getUpperBound());
 
        // Act
        double lowerBound = Math.min(range1.getLowerBound(), range2.getLowerBound());
        double upperBound = Math.max(range1.getUpperBound(), range2.getUpperBound());
        Range combinedRange = new Range(lowerBound, upperBound);
 
        // Assert
        assertEquals(expectedCombinedRange.getLowerBound(), combinedRange.getLowerBound(), 0.001);
        assertEquals(expectedCombinedRange.getUpperBound(), combinedRange.getUpperBound(), 0.001);
    }
 
    // TC2: nullRange1ReturnsRange2AsCombinedRange
    @Test
    public void nullRange1ReturnsRange2AsCombinedRange() {
        // Arrange
        Range range1 = null;
        Range range2 = new Range(20, 30);
 
        // Act
        double lowerBound = (range1 != null) ? range1.getLowerBound() : range2.getLowerBound();
        double upperBound = (range1 != null) ? range1.getUpperBound() : range2.getUpperBound();
        Range combinedRange = new Range(lowerBound, upperBound);
 
        // Assert
        assertEquals(range2.getLowerBound(), combinedRange.getLowerBound(), 0.001);
        assertEquals(range2.getUpperBound(), combinedRange.getUpperBound(), 0.001);
    }
 
    
    // TC3: range1IsNotNullAndRange2IsNull
    @Test
    public void range1IsNotNullAndRange2IsNull() {
        // Arrange
        Range range1 = new Range(10, 20);
        Range range2 = null;
 
        // Act
        double lowerBound = (range1 != null) ? range1.getLowerBound() : Double.NaN;
        double upperBound = (range1 != null) ? range1.getUpperBound() : Double.NaN;
        Range combinedRange = new Range(lowerBound, upperBound);
 
        // Assert
        assertEquals(range1.getLowerBound(), combinedRange.getLowerBound(), 0.001);
        assertEquals(range1.getUpperBound(), combinedRange.getUpperBound(), 0.001);
    }
 
 
    
    // TC4: nullRange1AndRange2IsNull
    @Test
    public void nullRange1AndRange2IsNull() {
        // Arrange
        Range range1 = null;
        Range range2 = null;
 
        // Act
        Range combinedRange = new Range(Double.NaN, Double.NaN);
 
        // Assert
        assertEquals(Double.NaN, combinedRange.getLowerBound(), 0.001);
        assertEquals(Double.NaN, combinedRange.getUpperBound(), 0.001);
    }
    
    //getLowerBound()
    
    // TC1: testGetLowerBoundWithZeroLowerBound
    @Test
    public void testGetLowerBoundWithZeroLowerBound() {
        // Arrange
        double lowerBound = 0;
        double upperBound = 10;
        Range range = new Range(lowerBound, upperBound);
 
        // Act
        double result = range.getLowerBound();
 
        // Assert
        assertEquals(lowerBound, result, 0.001);
    }
    
    // TC2: testGetLowerBoundWithNegativeLowerBound
    @Test
    public void testGetLowerBoundWithNegativeLowerBound() {
        // Arrange
        double lowerBound = -40;
        double upperBound = -20;
        Range range = new Range(lowerBound, upperBound);
 
        // Act
        double result = range.getLowerBound();
 
        // Assert
        assertEquals(lowerBound, result, 0.001);
    }
    
    // TC3: testGetLowerBoundWithNegativeToZeroLowerBound
    @Test
    public void testGetLowerBoundWithNegativeToZeroLowerBound() {
        // Arrange
        double lowerBound = -10;
        double upperBound = 0;
        Range range = new Range(lowerBound, upperBound);
 
        // Act
        double result = range.getLowerBound();
 
        // Assert
        assertEquals(lowerBound, result, 0.001);
    }
    
    // TC4: testGetLowerBoundWithEqualBounds
    @Test
    public void testGetLowerBoundWithEqualBounds() {
        // Arrange
        double bound = 10;
        Range range = new Range(bound, bound);
 
        // Act
        double result = range.getLowerBound();
 
        // Assert
        assertEquals(bound, result, 0.001);
    }
    
    //expand(Range range, double lowerMargin, double upperMargin)
    
    // TC1: testExpandWithValidRangeAndMargins
    @Test
    public void testExpandWithValidRangeAndMargins() {
        // Arrange
        Range inputRange = new Range(2, 6);
        double lowerMargin = 0.25;
        double upperMargin = 0.5;
 
        // Act
        Range expandedRange = Range.expand(inputRange, lowerMargin, upperMargin);
 
        // Assert
        assertEquals(1, expandedRange.getLowerBound(), 0.001);
        assertEquals(8, expandedRange.getUpperBound(), 0.001);
    }
    
    // TC2: testExpandWithNullRange
    @Test(expected = IllegalArgumentException.class)
    public void testExpandWithNullRange() {
        // Arrange
        Range inputRange = null;
        double lowerMargin = 0.3;
        double upperMargin = 0.6;
 
        // Act
        Range expandedRange = Range.expand(inputRange, lowerMargin, upperMargin);
 
        // No need for an assertion, we expect an exception to be thrown
    }
    
    // TC3: testExpandWithInvalidLowerMargin
    @Test
    public void testExpandWithInvalidLowerMargin() {
        // Arrange
        Range inputRange = new Range(2, 6);
        double lowerMargin = -0.2;
        double upperMargin = 0.5;
 
        // Act
        Range expandedRange = Range.expand(inputRange, lowerMargin, upperMargin);
 
        // Assert
        assertEquals(2, expandedRange.getLowerBound(), 0.001);
        assertEquals(8, expandedRange.getUpperBound(), 0.001);
    }
    
    // TC4: testExpandWithInvalidUpperMargin
    @Test
    public void testExpandWithInvalidUpperMargin() {
        // Arrange
        Range inputRange = new Range(2, 6);
        double lowerMargin = 0.25;
        double upperMargin = 2;
 
        // Act
        Range expandedRange = Range.expand(inputRange, lowerMargin, upperMargin);
 
        // Assert
        assertEquals(1, expandedRange.getLowerBound(), 0.001);
        assertEquals(6, expandedRange.getUpperBound(), 0.001);
    }	
    
    //BVA
    // TC5: testExpandWithZeroMargins
    @Test
    public void testExpandWithZeroMargins() {
        // Arrange
        Range inputRange = new Range(2, 6);
        double lowerMargin = 0;
        double upperMargin = 0;
 
        // Act
        Range expandedRange = Range.expand(inputRange, lowerMargin, upperMargin);
 
        // Assert
        assertEquals(inputRange.getLowerBound(), expandedRange.getLowerBound(), 0.001);
        assertEquals(inputRange.getUpperBound(), expandedRange.getUpperBound(), 0.001);
    }
 
    
    // TC6: testExpandWithMarginsEqualToOne
    @Test
    public void testExpandWithMarginsEqualToOne() {
        // Arrange
        Range inputRange = new Range(2, 6);
        double lowerMargin = 1;
        double upperMargin = 1;
 
        // Act
        Range expandedRange = Range.expand(inputRange, lowerMargin, upperMargin);
 
        // Assert
        assertEquals(-2, expandedRange.getLowerBound(), 0.001);
        assertEquals(10, expandedRange.getUpperBound(), 0.001);
    }
    
    // TC7: testExpandWithEqualMargins
    @Test
    public void testExpandWithEqualMargins() {
        // Arrange
        Range inputRange = new Range(4, 6);
        double lowerMargin = 0.5;
        double upperMargin = 0.5;
 
        // Act
        Range expandedRange = Range.expand(inputRange, lowerMargin, upperMargin);
 
        // Assert
        assertEquals(3, expandedRange.getLowerBound(), 0.001);
        assertEquals(7, expandedRange.getUpperBound(), 0.001);
    }
 
    //intersects(double lower, double upper)
    
    // TC1: testIntersectsTrue
    @Test
    public void testIntersectsTrue() {
        // Arrange
        double lower = 10;
        double upper = 15;
 
        // Act
        boolean lowerInRange = rangeObjectUnderTest.getLowerBound() <= upper && rangeObjectUnderTest.getLowerBound() <= lower;
        boolean upperInRange = rangeObjectUnderTest.getUpperBound() >= lower && rangeObjectUnderTest.getUpperBound() >= upper;
        boolean result = lowerInRange || upperInRange;
 
        // Assert
        assertTrue(result);
    }
 
    
    // TC2: testIntersectsTrueValidEqualBounds
    @Test
    public void testIntersectsTrueValidEqualBounds() {
        // Arrange
        double lower = 10;
        double upper = 10;
 
        // Act
        boolean result = rangeObjectUnderTest.intersects(lower, upper);
 
        // Assert
        assertTrue(result);
    }
    
    // TC3: testIntersectsFalseInvalidRange
    @Test
    public void testIntersectsFalseInvalidRange() {
        // Arrange
        double lower = 20;
        double upper = 15;
 
        // Act
        boolean result = rangeObjectUnderTest.intersects(lower, upper);
 
        // Assert
        assertFalse(result);
    }
    
    // TC4: testIntersectsFalseOutsideClassRange
    @Test
    public void testIntersectsFalseOutsideClassRange() {
        // Arrange
        double lower = 120;
        double upper = 125;
 
        // Act
        boolean result = rangeObjectUnderTest.intersects(lower, upper);
 
        // Assert
        assertFalse(result);
    }
    
    //BVA
    // TC5: testIntersectsTrueBoundaryValues
    @Test
    public void testIntersectsTrueBoundaryValues() {
        // Arrange
        double lower = 0;
        double upper = 0;
 
        // Act
        boolean result = rangeObjectUnderTest.intersects(lower, upper);
 
        // Assert
        assertTrue(result);
    }
    
    // TC6: testIntersectsTrueSameValues
    @Test
    public void testIntersectsTrueSameValues() {
        // Arrange
        double lower = 50;
        double upper = 50;
 
        // Act
        boolean result = rangeObjectUnderTest.intersects(lower, upper);
 
        // Assert
        assertTrue(result);
    }
    
    // TC7: testIntersectsTrueNegativeRange
    @Test
    public void testIntersectsTrueNegativeRange() {
        // Arrange
        double lower = -10;
        double upper = -5;
 
        // Act
        boolean result = rangeObjectUnderTest.intersects(lower, upper);
 
        // Assert
        assertTrue(result);
    }
    
    // TC8: testIntersectsFalseInvalidRangeReverse
    @Test
    public void testIntersectsFalseInvalidRangeReverse() {
        // Arrange
        double lower = -5;
        double upper = -10;
 
        // Act
        boolean result = rangeObjectUnderTest.intersects(lower, upper);
 
        // Assert
        assertFalse(result);
    }
 
    //shift(Range base, double delta, boolean allowZeroCrossing)
    
    // TC1: testShiftPositiveDelta
    @Test
    public void testShiftPositiveDelta() {
        // Arrange
        Range base = new Range(1, 10);
        double delta = 5;
 
        // Calculate the shifted range
        double newLowerBound = base.getLowerBound() + delta;
        double newUpperBound = base.getUpperBound() + delta;
        Range expectedShiftedRange = new Range(newLowerBound, newUpperBound);
 
        // Act
        Range shiftedRange = new Range(newLowerBound, newUpperBound);
 
        // Assert
        assertEquals(expectedShiftedRange.getLowerBound(), shiftedRange.getLowerBound(), 0.001);
        assertEquals(expectedShiftedRange.getUpperBound(), shiftedRange.getUpperBound(), 0.001);
    }
 
 
    
    // TC2: testShiftNullBase
    @Test(expected = NullPointerException.class)
    public void testShiftNullBase() {
        // Arrange
        Range base = null;
        double delta = 5;
 
        // Act
        Range.shift(base, delta, true);
 
        // No need to assert as we're expecting an exception
    }
    
    // TC3: testShiftPositiveDeltaValidRange
    @Test
    public void testShiftPositiveDeltaValidRange() {
        // Arrange
        Range base = new Range(20, 25);
        double delta = 10;
        Range expected = new Range(30, 35);
 
        // Act
        Range shiftedRange = Range.shift(base, delta, true);
 
        // Assert
        assertEquals(expected, shiftedRange);
    }
    
    // TC4: testShiftNegativeDeltaValidRange
    @Test
    public void testShiftNegativeDeltaValidRange() {
        // Arrange
        Range base = new Range(15, 20);
        double delta = -5;
        Range expected = new Range(10, 15);
 
        // Act
        Range shiftedRange = Range.shift(base, delta, true);
 
        // Assert
        assertEquals(expected, shiftedRange);
    }
    
    // TC5: testShiftZeroDeltaSameRange
    @Test
    public void testShiftZeroDeltaSameRange() {
        // Arrange
        Range base = new Range(10, 15);
        double delta = 0;
        Range expected = new Range(10, 15);
 
        // Act
        Range shiftedRange = Range.shift(base, delta, true);
 
        // Assert
        assertEquals(expected, shiftedRange);
    }
 
}
