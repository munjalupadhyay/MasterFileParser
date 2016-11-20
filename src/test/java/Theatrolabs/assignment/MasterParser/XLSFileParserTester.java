package Theatrolabs.assignment.MasterParser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class XLSFileParserTester {

	XLSFileParser xlsFP;
	Method open;
	Method validate;
	String workingDir;
	File inputWorkbook;
	Workbook w;
	Cell cell0,cell1,cell2,cell3;
	Sheet sheet;
	XLSFileParser assignment1xlsFP;
	
	@Before
	public void setUpClass() {
	    //executed only once, before the first test
		workingDir = System.getProperty("user.dir");
		//System.out.println(workingDir);
		//System.out.println("...............................");
		
		xlsFP = new XLSFileParser(workingDir+"/src/test/resources/assignment.XLS");
		try{
			
			open=XLSFileParser.class.getDeclaredMethod("open",String.class);
			open.setAccessible(true);
			validate=XLSFileParser.class.getDeclaredMethod("validate",Sheet.class,int.class);
			validate.setAccessible(true);
		}
		catch(Exception ex){
			//ex.printStackTrace();
			System.out.println("Some problem is setUpClass()");
		}
		
		// setting up the test file , assignment1
		String assignment1Str=workingDir+"/src/test/resources/assignment1.XLS";
		assignment1xlsFP=new XLSFileParser(assignment1Str); 
		
		inputWorkbook = new File(assignment1Str);
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			this.sheet = w.getSheet(0);
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void XLS_open_fileIsNULL(){
		String str=null;
    	try{
    		Boolean bool=(Boolean) open.invoke(xlsFP,str);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
	}
	
	@Test
	public void XLS_open_fileNotXLS(){
		String str=workingDir+"/src/test/resources/assignment.xlsx";
    	try{
    		Boolean bool=(Boolean) open.invoke(xlsFP,str);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
	}
	
	
	@Test
	public void XLS_open_fileIsXLS(){
		String str=workingDir+"/src/test/resources/assignment.XLS";
    	try{
    		Boolean bool=(Boolean) open.invoke(xlsFP,str);
    		assertTrue( bool);
    	}
    	catch(Exception ex){
    		//ex.printStackTrace();
    	}
	}
	
	@Test
	public void XLS_validate_emptyCell() throws Exception{
		
		int lineNumber=3;
		
    	try{
    		//createCell(str,lineNumber);
    		Boolean bool=(Boolean) validate.invoke(assignment1xlsFP,this.sheet,lineNumber);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
	}
	
	@Test
	public void XLS_validate_outOfBoundCell(){
		Exception e=null;
		
		int lineNumber=50;
		
    	try{
    		
    		validate.invoke(assignment1xlsFP,this.sheet,lineNumber);
    	}
    	catch(Exception ex){
    		e=ex;
    		ex.printStackTrace();
    	}
    	assertEquals(null,e);
    	
	}
	
	@Test
	public void XLS_validate_notNumberCell1() throws Exception{
		
		int lineNumber=4;
		 
    	try{
    		
    		Boolean bool=(Boolean) validate.invoke(assignment1xlsFP,this.sheet,lineNumber);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
	}
	@Test
	public void XLS_validate_notNumberCell2() throws Exception{
		
		int lineNumber=5;
		 
    	try{
    		
    		Boolean bool=(Boolean) validate.invoke(assignment1xlsFP,this.sheet,lineNumber);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
	}
	@Test
	public void XLS_validate_notNumberCell3() throws Exception{
		
		int lineNumber=6;
		
    	try{
    		
    		Boolean bool=(Boolean) validate.invoke(assignment1xlsFP,this.sheet,lineNumber);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
	}
	@Test
	public void XLS_validate_notLabel() throws Exception{
		
		int lineNumber=7;
		 
    	try{
    		
    		Boolean bool=(Boolean) validate.invoke(assignment1xlsFP,this.sheet,lineNumber);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
	}
	@Test
	public void XLS_validate_notInt1() throws Exception{
		
		int lineNumber=10;
		 
    	try{
    		
    		Boolean bool=(Boolean) validate.invoke(assignment1xlsFP,this.sheet,lineNumber);
    		assertFalse( bool);
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
	}
	@Test
	public void XLS_validate_notInt2() throws Exception{
	
	int lineNumber=11;
	 
	try{
		
		Boolean bool=(Boolean) validate.invoke(assignment1xlsFP,this.sheet,lineNumber);
		assertFalse( bool);
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	}
	@Test
	public void XLS_validate_notInt3() throws Exception{
	
	int lineNumber=11;
	 
	try{
		
		Boolean bool=(Boolean) validate.invoke(assignment1xlsFP,this.sheet,lineNumber);
		assertFalse( bool);
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	}
	
	@Test
	public void XLS_read_validData() throws Exception{
	
	try{
		
		Class cls = assignment1xlsFP.getClass();
		
		Data data=new Data("Tamil nadu",987656467,56876,985432);
		assignment1xlsFP.read();
		Field field=cls.getDeclaredField("list");
		field.setAccessible(true);
		List<Data> list=(List<Data>) field.get(assignment1xlsFP);
		assertFalse( (data).equals(list.get(0)));
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	}
	
	
}
