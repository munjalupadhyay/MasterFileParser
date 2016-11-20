package Theatrolabs.assignment.MasterParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


class CSVFileParser extends AbstractFileParser {

	private List<Data> list;
	BufferedReader br = null;
    String splitOperator = ",";
	String fileName;
	int numberOfFields=4;
	int lineNumber=0;

	CSVFileParser(String fileName){
		list = new ArrayList<Data>();
		this.fileName=fileName;
		open(fileName);
	}

	public void read() {
		if( !validateFileType(fileName) ){
			System.out.println("please provide proper(.CSV) extension");
			return;
		}
		try{
			String line = "";
			lineNumber=0;
			while ((line = br.readLine()) != null) {
				if(validate(line)){
					
					String[] arr = line.split(splitOperator);
					int val1=Integer.parseInt(arr[1].trim());
					int val2=Integer.parseInt(arr[2].trim());
					int val3=Integer.parseInt(arr[3].trim());
					Data temp=new Data(arr[0],val1,val2,val3);
					list.add(temp);
				}
	                	lineNumber=lineNumber+1;
	         }

		}
		catch (IOException e) {
				System.out.println("IOEXception in read()");
           		//e.printStackTrace();
        	} 

	}

	public void display() {
		super.instantiate(list);
		super.display();
	}
	
	public void write() {
		// TODO Auto-generated method stub
		
	}

	public void edit() {
		// TODO Auto-generated method stub
		
	}
	private void open(String str){
		if(str==null){
			System.out.println("pelase provide file name");
			System.exit(0);
			//return false;
		}
		try{
			br = new BufferedReader(new FileReader(str));
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found");
			//e.printStackTrace();
		} 
		
	}


	private void close(){
		
            		if (br != null) {
                		try {
                    			br.close();
                		} catch (IOException e) {
                    			//e.printStackTrace();
                		}
            		}
	}

	private boolean validateFileType(String fileName){
		if(fileName == null){
			return false;
		}
		if( !(fileName.toUpperCase().endsWith(".CSV")) ){
			return false;
		}
		return true;
	}
	
	private boolean validate(String str){

		// check if the line is empty, if so return false as result to validate()
		boolean result=true;
		String temp = str.trim();
		if(temp.isEmpty()){
			return false;
		}
		
		String[] arr = str.split(splitOperator);
		
		// check if 1st, 2nd and 3rd entries are of type int
		try{
			int x = Integer.parseInt(arr[1]);
			x=Integer.parseInt(arr[2]);
			x=Integer.parseInt(arr[3]);
		}
		catch(ArrayIndexOutOfBoundsException e){
			//e.printStackTrace();
			System.out.println("Not sufficient data @ line# "+lineNumber);
			result= false;
		}
		catch(NumberFormatException e){
			//e.printStackTrace();
			System.out.println("Not an Integer @ line# "+lineNumber);
			result= false;
		}
		if(result!=true){
			return false;
		}
		return true;
	}
}
