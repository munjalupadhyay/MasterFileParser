package Theatrolabs.assignment.MasterParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

class XLSFileParser extends AbstractFileParser {

	 private String inputFile;
	 private List<Data> list;
	 int lineNumber=0;
	 File inputWorkbook;
	 Workbook w;

     XLSFileParser(String fileName) {
		// TODO Auto-generated constructor stub
    	 this.inputFile = fileName;
    	 list = new ArrayList<Data>();
	}

    public void read()  {
    	if( !open(inputFile)){
    		System.out.println("something went WRONG");
    		return;
    	}
             
             
    	try {
                     
    		// Get the first sheet
            Sheet sheet = w.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                    	 	
            lineNumber=i;
                                  
            if(validate(sheet,i)){
            	String stateName=null;
                int totalPop=0;
                int malePop=0;
                int femalePop=0;
                		  	   //  getCell(column,row)
                Cell cell0 = sheet.getCell(0, i);
                Cell cell1 = sheet.getCell(1, i);
                Cell cell2 = sheet.getCell(2, i);
                Cell cell3 = sheet.getCell(3, i);
                                         
                stateName=cell0.getContents();
                totalPop=Integer.parseInt(cell1.getContents().trim());
                malePop=Integer.parseInt(cell2.getContents().trim());
                femalePop=Integer.parseInt(cell3.getContents().trim());
                list.add(new Data(stateName,totalPop,malePop,femalePop));
            	}
                                     
                                     
                                     
            }
    	} 
    	catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void display() {
 		super.instantiate(list);
 		super.display();
 	}
    
   
    private boolean open(String str){
    	if(str==null){
			System.out.println("pelase provide file name");
			//System.exit(0);
			return false;
		}
    	else if( !(str.toUpperCase().endsWith(".XLS")) ){
    		System.out.println("pelase provide file name with .XLS extension");
			return false;
		}
		try{
			inputWorkbook = new File(str);
			w = Workbook.getWorkbook(inputWorkbook);
		}
		catch (BiffException e) {
			System.out.println("File Not Found");
            //e.printStackTrace();
			return false;
		}
		catch (IOException e) {
			System.out.println("IO EXCEPTION");
             //e.printStackTrace();
			return false;
		 }
		catch(Exception e){
			System.out.println("something went wrong @open");
			//e.printStackTrace();
			return false;
		} 
		
		return true;
    }
    
    private boolean validate(Sheet sheet,int rowIndex){
    	boolean result=true;
    	Cell cell0=null,cell1=null,cell2=null,cell3=null;
    	
    	if(sheet==null){
    		return false;
    	}
    	
    	try{
    	//  getCell(column,row)
    		cell0 = sheet.getCell(0, rowIndex);
    		cell1 = sheet.getCell(1, rowIndex);
    		cell2 = sheet.getCell(2, rowIndex);
    		cell3 = sheet.getCell(3, rowIndex);
    	}
    	catch(ArrayIndexOutOfBoundsException ex){
    		System.out.println("No data exists at specified @row "+rowIndex);
    		result= false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		System.out.println("something went went while parsing @row "+rowIndex);
    		result= false;
    	}
    	
    	if(result== false){
    		return false;
    	}
        
    	if( (cell0.getType() == CellType.ERROR 
    			&& cell1.getType() == CellType.ERROR 
    			&& cell2.getType() == CellType.ERROR 
    			&& cell3.getType() == CellType.ERROR) ){
    		System.out.println("ERROR @ line number "+lineNumber);
    		return false;
    	}
    	
    	if( (cell0.getType() == CellType.EMPTY
    			&& cell1.getType() == CellType.EMPTY 
    			&& cell2.getType() == CellType.EMPTY 
    			&& cell3.getType() == CellType.EMPTY) ){
    		System.out.println("EMPTY LINE @ line number "+lineNumber);
    		return false;
    	}
    	
    	CellType type = cell0.getType();
    	if (!(type == CellType.LABEL)) {
    		System.out.println("value is not string @ line number "+lineNumber);
            return false;
    	}
    	
    	
    	if ( !(cell1.getType() == CellType.NUMBER 
    			&& cell2.getType() == CellType.NUMBER 
    			&& cell3.getType() == CellType.NUMBER) ) {
    		System.out.println("value is not number @ line number "+lineNumber);
            return false;
    	}
    	
    	try{
			int x = Integer.parseInt(cell1.getContents().trim());
			x=Integer.parseInt(cell2.getContents().trim());
			x=Integer.parseInt(cell3.getContents().trim());
		}
		catch(NumberFormatException e){
			//e.printStackTrace();
			System.out.println("Not an Integer @ line number "+lineNumber);
			result= false;
		}
    	if(result == false){
    		return false;
    	}
    	

    	return true;
    	
    }
    
	public void write() {
		// TODO Auto-generated method stub

	}

	public void edit() {
		// TODO Auto-generated method stub

	}

}
