package Theatrolabs.assignment.MasterParser;

class Data {
	String stateName;
	int totalPop;
	int menPop;
	int womenPop;

	Data(String stateName,int totalPop,int menPop,int womenPop){
		this.stateName=stateName;
		this.totalPop=totalPop;
		this.menPop=menPop;
		this.womenPop=womenPop;
	}
	
	public boolean equals(Data data){
		return (data.stateName.equals(this.stateName)
				&& data.totalPop == this.totalPop
				&& data.menPop == this.menPop
				&& data.womenPop == this.womenPop);
	}

}