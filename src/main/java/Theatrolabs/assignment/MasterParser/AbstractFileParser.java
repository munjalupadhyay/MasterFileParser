package Theatrolabs.assignment.MasterParser;
import java.util.*;

abstract class AbstractFileParser implements FileParser {

		private List<Data> list;
		
		void instantiate(List<Data> list){
			this.list=list;
		}
	
		public void display() {
			
			for(Data ele : list){
				System.out.println();
				System.out.println("-----------------------------------");
				System.out.println("state : "+ele.stateName);
				System.out.println("Population : "+ele.totalPop);
				System.out.println("Men : "+ele.menPop);
				System.out.println("women : "+ele.womenPop);
				System.out.println("-----------------------------------");
			}

	}


}
