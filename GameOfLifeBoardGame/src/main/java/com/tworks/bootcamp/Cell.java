package com.tworks.bootcamp;

public class Cell {
	String orgState;
	public void setOrgState(String orgState) {
		this.orgState = orgState;
	}
	
	public String getOrgState() {
		return orgState;
	}


	public Cell(String state) {
		super();
		this.orgState = state;
	}


	public String getNewState(int numOfLiveNeighbours) {
		String newState = "";
		if((numOfLiveNeighbours < 2) || (numOfLiveNeighbours > 3)) {
			newState = "-";
		}
		else if(numOfLiveNeighbours == 2){
			newState = orgState;
		}
		else {
			newState = "X";
		}
		return newState;
	}

}
