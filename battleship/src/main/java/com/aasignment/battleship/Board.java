package com.aasignment.battleship;

public class Board {
	private String playerName;
	private int board[][];
	private int width;
	private int length;
	
	Board(String playerName, int width, char length){
		this.playerName = playerName;
		this.width = width;
		this.length = (length - 'A') + 1;
	}

	public void initBoard() {
		this.board = new int[this.width][this.length];
		for(int i=0;i<width; ++i){
			for(int j=0;j<this.length;++j) {
				this.board[i][j] = -1;
			}
		}
		
	}
	
	
	public void displayBoard() {
		for(int i=0;i<width; ++i){
			for(int j=0;j<this.length;++j) {
				if(this.board[i][j] == -1) {
					System.out.print("E |");
				}
				else if(this.board[i][j] == 0) {
					System.out.print("X |");
				}
				else if(this.board[i][j] == 1) {
					System.out.print("P |");
				}
				else if(this.board[i][j] == 2) {
					System.out.print("Q |");
				}
			}
			System.out.println("\n----------------------------------------");
		}
		
	}
	
	private int getRowIndex(char code){
		return code - 'A';
	}
	
	private int getColIndex(int id) {
		return id -1;
	}
	
	public int getValueInBoard(String cellCode) {
		int rowId = getRowIndex(cellCode.charAt(0));
		int colId = getColIndex(cellCode.charAt(1));
		return board[rowId][colId];
	}
	
	public void setValueInBoard(int rowId, int colId, char ShipType) {
		
		if(ShipType == 'P') {
			board[rowId][colId] = 1;
		}
		else {
			board[rowId][colId] = 2;
		}
	}
	
	public Boolean allShipsDestroyed() {
		for(int i=0;i<width; ++i){
			for(int j=0;j<this.length;++j) {
				if(this.board[i][j] > 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Boolean isPossiblePlacement(String source, char shipType, int shipWidth, int shipHeight, String initCellCode) {
		int initRowId = getRowIndex(initCellCode.charAt(0));
		int initColId = getColIndex(initCellCode.charAt(1) - '0');
		
		if(((initColId + shipWidth) > this.width) || ((initRowId + shipHeight) > this.length)){
			System.out.println("Error: Cannot place ship on " + source + " board");
			return false;
		}
		return true;
	}
	
	public Boolean placeShip(char shipType, int shipWidth, int shipHeight, String initCellCode){
		int initRowId = getRowIndex(initCellCode.charAt(0));
		int initColId = getColIndex(initCellCode.charAt(1) - '0');

		int currRowId = -1;
		int currColId = -1;
		
		for(int i = initColId; shipWidth > 0; i++) {
			
			currColId = i;
			for(int j = initRowId; shipHeight > 0; j++) {
				currRowId = j;
				--shipHeight;
			}
			--shipWidth;
			setValueInBoard(currRowId, currColId, shipType);
		}
		return true;
	}
	
	public String fireMissile(String boardLoc) {
		int rowIdx = getRowIndex(boardLoc.charAt(0));
		int colIdx = getColIndex(boardLoc.charAt(1) - '0');
		
		if(this.board[rowIdx][colIdx] > 0) {
			--this.board[rowIdx][colIdx];
			return "hit";
		}
		return "miss";
	}
	
	
}
