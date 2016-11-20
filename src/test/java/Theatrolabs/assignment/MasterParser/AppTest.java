package Theatrolabs.assignment.MasterParser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({
   CSVFileParserTester.class,
   XLSFileParserTester.class,
   MasterFileParserTester.class
})

public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	//CSVFileParserTester csv= new CSVFileParserTester("/home/munjal/Desktop/TheatroLabs/assignment.csv");
	
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	//TestSuite suite = new TestSuite("Test ExpenseTest");
        return new TestSuite( AppTest.class );
    	//suite.addTest(new AppTest("testApp"));
    	// suite.addTest(AppTest.class);
         //suite.addTest(CSVFileParserTesterTester.class);
    	//suite.addTest(new AppTest("CSV_Open_NullTest"));
    	//return suite;
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    
    
}
