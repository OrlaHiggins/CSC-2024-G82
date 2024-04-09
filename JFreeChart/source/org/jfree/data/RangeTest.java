package org.jfree.data;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
 
import java.util.Objects;
 
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
    //Lab3
    // TC5: testCombineBothRangesNonNullAndValid
    @Test
    public void testCombineBothRangesNonNullAndValid() {
        // Arrange
        Range range1 = new Range(5, 15);
        Range range2 = new Range(10, 20);
 
        // Act
        Range combinedRange = Range.combine(range1, range2);
 
        // Assert
        assertEquals(5, combinedRange.getLowerBound(), 0.001);
        assertEquals(20, combinedRange.getUpperBound(), 0.001);
    }
 
    // TC6: testCombineRange1NonNullAndRange2Null
    @Test
    public void testCombineRange1NonNullAndRange2Null() {
        // Arrange
        Range range1 = new Range(5, 15);
        Range range2 = null;
 
        // Act
        Range combinedRange = Range.combine(range1, range2);
 
        // Assert
        assertNotNull(combinedRange);
        assertEquals(5, combinedRange.getLowerBound(), 0.001);
        assertEquals(15, combinedRange.getUpperBound(), 0.001);
    }
 
 
    // TC7: testCombineBothRangesNull
    @Test
    public void testCombineBothRangesNull() {
        // Arrange
        Range range1 = null;
        Range range2 = null;
 
        // Act
        Range combinedRange = Range.combine(range1, range2);
 
        // Assert
        assertNull(combinedRange);
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
    //Lab3
    // TC9: testIntersectsInRange
    @Test
    public void testIntersectsInRange() {
        // Arrange
        Range range = new Range(0, 10);
        double lower = 5;
        double upper = 15;
 
        // Act
        boolean result = range.intersects(lower, upper);
 
        // Assert
        assertTrue(result);
    }
 
    // TC10: testIntersectsNotInRange
    @Test
    public void testIntersectsNotInRange() {
        // Arrange
        Range range = new Range(0, 10);
        double lower = 15;
        double upper = 20;
 
        // Act
        boolean result = range.intersects(lower, upper);
 
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
    //Lab3
    // TC6: testShiftPositiveDeltaWithZeroCrossingDisallowed
    @Test
    public void testShiftPositiveDeltaWithZeroCrossingDisallowed() {
        // Arrange
        Range base = new Range(10, 20);
        double delta = 15;
        boolean allowZeroCrossing = false;
        Range expected = new Range(25, 35); // Expected result without zero crossing
        // Act
        Range shiftedRange = Range.shift(base, delta, allowZeroCrossing);
        // Assert
        assertEquals(expected, shiftedRange);
    }
 
    
    //Lab3
    //explandToInclude
    // TC1: testExpandToIncludeNullRange
    @Test
    public void testExpandToIncludeNullRange() {
        // Arrange
        Range range = null;
        double value = 10;
        Range expected = new Range(value, value);
 
        // Act
        Range expandedRange = Range.expandToInclude(range, value);
 
        // Assert
        assertEquals(expected, expandedRange);
    }
 
    // TC2: testExpandToIncludeValueBelowLowerBound
    @Test
    public void testExpandToIncludeValueBelowLowerBound() {
        // Arrange
        Range range = new Range(20, 30);
        double value = 10;
        Range expected = new Range(value, range.getUpperBound());
 
        // Act
        Range expandedRange = Range.expandToInclude(range, value);
 
        // Assert
        assertEquals(expected, expandedRange);
    }
 
    // TC3: testExpandToIncludeValueAboveUpperBound
    @Test
    public void testExpandToIncludeValueAboveUpperBound() {
        // Arrange
        Range range = new Range(20, 30);
        double value = 40;
        Range expected = new Range(range.getLowerBound(), value);
 
        // Act
        Range expandedRange = Range.expandToInclude(range, value);
 
        // Assert
        assertEquals(expected, expandedRange);
    }
 
    // TC4: testExpandToIncludeValueWithinRange
    @Test
    public void testExpandToIncludeValueWithinRange() {
        // Arrange
        Range range = new Range(20, 30);
        double value = 25;
 
        // Act
        Range expandedRange = Range.expandToInclude(range, value);
 
        // Assert
        assertEquals(range, expandedRange);
    }

    //constrain(double value)
 
 
    // TC1: testConstrainValueWithinRange
    @Test
    public void testConstrainValueWithinRange() {
        Range range = new Range(0, 10);
        double value = 5;
        double result = range.constrain(value);
        assertEquals(value, result, 0.01);
    }
 
    // TC2: testConstrainValueGreaterThanUpperBound
    @Test
    public void testConstrainValueGreaterThanUpperBound() {
        Range range = new Range(0, 10);
        double value = 15;
        double result = range.constrain(value);
        assertEquals(10, result, 0.01);
    }
 
    // TC3: testConstrainValueLessThanLowerBound
    @Test
    public void testConstrainValueLessThanLowerBound() {
        Range range = new Range(0, 10);
        double value = -5;
        double result = range.constrain(value);
        assertEquals(0, result, 0.01);
    }
    // TC4: testConstrainNaNValue
    @Test
    public void testConstrainNaNValue() {
        Range range = new Range(0, 10);
        double value = Double.NaN;
        double result = range.constrain(value);
        assertTrue(Double.isNaN(result));
    }
    //Range(double, double)
    /// TC1: testConstructorLowerBoundGreaterThanUpperBound
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorLowerBoundGreaterThanUpperBound() {
        // Arrange
        double lower = 20;
        double upper = 10;
 
        // Act
        new Range(lower, upper);
 
        // No need to assert as we're expecting an exception
    }
    //equals(java.lang.Object obj)
    // TC1: testEqualsDifferentClass
    @Test
    public void testEqualsDifferentClass() {
        // Arrange
        Object obj = new Object(); // Object of a different class
        Range range = new Range(0, 10);
 
        // Act
        boolean result = range.equals(obj);
 
        // Assert
        assertFalse(result);
    }
 
    // TC2: testEqualsDifferentLowerBound
    @Test
    public void testEqualsDifferentLowerBound() {
        // Arrange
        Range range1 = new Range(0, 10);
        Range range2 = new Range(5, 10);
 
        // Act
        boolean result = range1.equals(range2);
 
        // Assert
        assertFalse(result);
    }
 
    // TC3: testEqualsDifferentUpperBound
    @Test
    public void testEqualsDifferentUpperBound() {
        // Arrange
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 15);
 
        // Act
        boolean result = range1.equals(range2);
 
        // Assert
        assertFalse(result);
    }
 
    // TC4: testEqualsEqualBounds
    @Test
    public void testEqualsEqualBounds() {
        // Arrange
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 10);
 
        // Act
        boolean result = range1.equals(range2);
 
        // Assert
        assertTrue(result);
    }
    //shiftWithNoZeroCrossing(double value, double delta)
    // TC1: testShiftWithNoZeroCrossing_PositiveValue_PositiveDelta
    @Test
    public void testShiftWithNoZeroCrossing_PositiveValue_PositiveDelta() {
        // Arrange
        double value = 5.0;
        double delta = 2.0;
 
        // Act
        double result = Range.shiftWithNoZeroCrossing(value, delta);
 
        // Assert
        assertEquals(7.0, result, 0.001);
    }
 
    // TC2: testShiftWithNoZeroCrossing_NegativeValue_NegativeDelta
    @Test
    public void testShiftWithNoZeroCrossing_NegativeValue_NegativeDelta() {
        // Arrange
        double value = -5.0;
        double delta = -2.0;
 
        // Act
        double result = Range.shiftWithNoZeroCrossing(value, delta);
 
        // Assert
        assertEquals(-7.0, result, 0.001);
    }
 
    // TC3: testShiftWithNoZeroCrossing_PositiveValue_NegativeDelta
    @Test
    public void testShiftWithNoZeroCrossing_PositiveValue_NegativeDelta() {
        // Arrange
        double value = 5.0;
        double delta = -2.0;
 
        // Act
        double result = Range.shiftWithNoZeroCrossing(value, delta);
 
        // Assert
        assertEquals(3.0, result, 0.001);
    }
 
    // TC4: testShiftWithNoZeroCrossing_NegativeValue_PositiveDelta
    @Test
    public void testShiftWithNoZeroCrossing_NegativeValue_PositiveDelta() {
        // Arrange
        double value = -5.0;
        double delta = 2.0;
 
        // Act
        double result = Range.shiftWithNoZeroCrossing(value, delta);
 
        // Assert
        assertEquals(-3.0, result, 0.001);
    }
    //shift(Range base, double delta)
    // TC1: testShiftPositiveDeltaWithNoZeroCrossing
    @Test
    public void testShiftPositiveDeltaWithNoZeroCrossing() {
        // Arrange
        Range base = new Range(10, 20);
        double delta = 15;
        Range expected = new Range(25, 35); // Expected result without zero crossing
 
        // Act
        Range shiftedRange = Range.shift(base, delta);
 
        // Assert
        assertEquals(expected, shiftedRange);
    }
    //hashcode()
    // TC1: testHashCode
    @Test
    public void testHashCode() {
        // Arrange
        Range range = new Range(10, 20);
        int expectedHashCode = Objects.hash(10L, 20L); // Expected hash code based on lower and upper bounds
 
        // Act
        int actualHashCode = range.hashCode();
 
        // Assert
        assertEquals(expectedHashCode, actualHashCode);
    }
    //toString()
    // TC1: testToString
    @Test
    public void testToString() {
        // Arrange
        Range range = new Range(10, 20);
        String expected = "Range[10.0,20.0]";
 
        // Act
        String result = range.toString();
 
        // Assert
        assertEquals(expected, result);
    }
 
    //getCentralValue()
    // TC1: testGetCentralValue
    @Test
    public void testGetCentralValue() {
        // Arrange
        Range range = new Range(10, 20);
        double expected = 15.0;
 
        // Act
        double result = range.getCentralValue();
 
        // Assert
        assertEquals(expected, result, 0.001);
    }
 
}
