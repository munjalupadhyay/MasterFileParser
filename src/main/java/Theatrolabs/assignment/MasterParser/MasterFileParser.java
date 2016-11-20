package Theatrolabs.assignment.MasterParser;



/**
 * Hello world!
 *
 */
public class MasterFileParser 
{
	FileParser fr;
	
	MasterFileParser(String fileName){
		String str=new String(fileName);
		
		if(str.toUpperCase().endsWith(".CSV")){
			fr=new CSVFileParser(fileName);
		}
		else if(str.toUpperCase().endsWith(".XLS")){
			fr=new XLSFileParser(fileName);
		}
		
		else if(str.toUpperCase().endsWith(".XLSX")){
			System.out.println("please provide XLS extension file");
		}
		else{
			System.out.println("please provide CSV/XLS extension file");
		}
		
		
	}


	public void read(){
		fr.read();
	}

	public void display(){
		fr.display();
	}

	/* consider them as enhancement features */
	public void write(){

	}

	public void edit(){

	}	
}
