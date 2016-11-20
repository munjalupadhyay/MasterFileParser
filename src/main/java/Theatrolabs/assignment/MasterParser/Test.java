package Theatrolabs.assignment.MasterParser;

public class Test {
	public static void main(String args[]){
		MasterFileParser fr=new MasterFileParser("/home/munjal/Desktop/TheatroLabs/assignment.csv");
		///
		//MasterFileParser fr=new MasterFileParser("/home/munjal/Desktop/TheatroLabs/jexcelapi/assignment.XLS");
		fr.read();
		fr.display();	
	}
}
