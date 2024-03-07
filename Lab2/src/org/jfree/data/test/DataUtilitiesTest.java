package org.jfree.data.test; 

	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertTrue;
	import static org.junit.Assert.fail;
	import org.jfree.data.DataUtilities;
	import org.jfree.data.DefaultKeyedValues2D;
	import org.jfree.data.Values2D;
	import org.junit.Test;
	import junit.framework.TestCase;
 

	public class DataUtilitiesTest extends TestCase { 
		
		private Values2D values2D; 
		

		public void setUp() 
		{ 
			DefaultKeyedValues2D testValues = new DefaultKeyedValues2D(); 
			values2D = testValues; 
			testValues.addValue(1, 0, 0); 
			testValues.addValue(4, 1, 0); 
			} 
		

		public void tearDown()
		{
			values2D = null; 
			}
		
		@Test 
		public void testValidDataAndCalculateColumnTotal() { 
			assertEquals("Wrong sum returned. It should be 5.0", 
					5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
		
	
		}
	
		public void testNullDataColumnTotal() 
		{ 
			try 
			{ 
				DataUtilities.calculateColumnTotal(null, 0); 
				fail("No exception thrown");
				}
			catch (Exception e) 
			{
				assertTrue("Incorrect exception type thrown", 
						e.getClass().equals(IllegalArgumentException.class)); 
				} 
			
	}
}
	

