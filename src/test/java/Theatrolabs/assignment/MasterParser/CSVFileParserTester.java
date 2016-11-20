package Theatrolabs.assignment.MasterParser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.lang.reflect.*;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class CSVFileParserTester  {

	CSVFileParser csvFP;
	Method validate;
	Method validateFileType;
	Method open;
	String workingDir;
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	/*@Test
	public void CSV_Open_NullTest(){
    	assertFalse( csv.open(null));
    }*/
	
	@Before
	public void setUpClass() {
	    //executed only once, before the first test
		workingDir = System.getProperty("user.dir");
		//System.out.println(workingDir);
		//System.out.println("...............................");
		
		csvFP = new CSVFileParser(workingDir+"/src/test/resources/assignment.csv");
		try{
			validate = CSVFileParser.class.getDeclaredMethod("validate", String.class);
			validate.setAccessible(true);
			validateFileType=CSVFileParser.class.getDeclaredMethod("validateFileType",String.class);
			validateFileType.setAccessible(true);
			open=CSVFileParser.class.getDeclaredMethod("open",String.class);
			open.setAccessible(true);
		}
		catch(Exception ex){
			//ex.printStackTrace();
			System.out.println("Some problem is setUpClass()");
		}
		
	}
	
	@Test//(expected=FileNotFoundException.class)
    public void CSV_Open_FileNotFoundTest() {
		// as I am not throwing exception in open(), I am catching it in the method only.
		Exception e=null;
		try{
			// NOT WORKING...
			
			open.invoke(csvFP,workingDir+"/src/test/resources/assignmentttt.csv");
		}
		catch(Exception ex){
			e=ex;
			//System.out.println("hahahaahah................................");
    		//ex.printStackTrace();
    		
    	}
		assertEquals(null,e);
    }
	
	@Test
    public void CSV_validate_FileIsNULL(){
    	//csv= new CSVFileParserTester(null);
    	//assertFalse( csv.validateFileType());
    	String str=null;
    	try{
    		Boolean bool=(Boolean) validateFileType.invoke(csvFP,str);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
    }
	@Test
    public void CSV_validate_FileNotXSL(){
    	//csv= new CSVFileParserTester("/something.csv");
    	//assertTrue( csv.validateFileType());
    	try{
    		Boolean bool=(Boolean) validateFileType.invoke(csvFP, new String("/something.csv"));
    		assertTrue( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
    }
	@Test
    public void CSV_validate_NULLLine(){
    	//assertFalse( csv.validate(null));
		// NOT WORKING PROPERLY
		String str=null;
    	try{
    		Boolean bool=(Boolean) validate.invoke(csvFP,str);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
    }
	@Test
    public void CSV_validate_emptyLine(){
    	//assertFalse( csv.validate(""));
    	try{
    		Boolean bool=(Boolean) validate.invoke(csvFP, new String(""));
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
    }
	@Test
    public void CSV_validate_NotNumber(){
    	//assertFalse( csv.validate("munj,1,mun,r"));
    	try{
    		Boolean bool=(Boolean) validate.invoke(csvFP, new String("munj,1,mun,r"));
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
    }
	@Test
    public void CSV_validate_containsSpaces(){
    	//assertFalse( csv.validate("munj,  1  ,  3  ,  6  "));
    	try{
    		Boolean bool=(Boolean) validate.invoke(csvFP, new String("munj,  1  ,  3  ,  6  "));
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
    }
	@Test
    public void CSV_validate_inSufficientData1(){
    	//assertFalse( csv.validate("munj,  1 , "));
    	try{
    		Boolean bool=(Boolean) validate.invoke(csvFP, new String("munj,  1 , "));
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
    }
	@Test
    public void CSV_validate_inSufficientData2(){
    	//assertFalse( csv.validate("munj,  1 "));
    	try{
    		Boolean bool=(Boolean) validate.invoke(csvFP, new String("munj,  1 "));
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
    }
	@Test
    public void CSV_validate_validString(){
    	//assertTrue( csv.validate("munj,1,2,3"));
    	try{
    		Boolean bool=(Boolean) validate.invoke(csvFP, new String("munj,1,2,3"));
    		assertTrue( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
    		
    }
	@Test
    public void CSV_dataObjectCorrectness(){
		try{
			
				Class cls = csvFP.getClass();
			
				Data data=new Data("munj",1,2,4);
				csvFP.read();
				Field field=cls.getDeclaredField("list");
				field.setAccessible(true);
				List<Data> list=(List<Data>) field.get(csvFP);
			
				assertFalse( (data).equals(list.get(0)));
			}
			catch(Exception e) {
		        System.out.println(e.toString());
		     }
    }
	
	
	@Test
    public void CSV_CSVFileParser_fileName(){
		
		// for more see :http://saturnboy.com/2010/11/testing-private-methods-in-java/
		// for more see : http://tutorials.jenkov.com/java-reflection/private-fields-and-methods.html		
		try{
				Class cls = csvFP.getClass();
			
				Data data=new Data("munj",1,2,4);
				csvFP.read();
				Field field=cls.getDeclaredField("fileName");
				field.setAccessible(true);
				String fileName=(String) field.get(csvFP);
			
				assertTrue(fileName.equals(workingDir+"/src/test/resources/assignment.csv") );
			}
			catch(Exception e) {
		        System.out.println(e.toString());
		     }
		
    }
}
