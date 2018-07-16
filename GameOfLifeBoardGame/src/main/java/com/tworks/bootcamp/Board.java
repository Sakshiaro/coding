package com.tworks.bootcamp;

import java.util.Scanner;

public class Board {
	private Cell [][] board;
	private int rows;
	private int cols;
	private int max;
	
	public void initialiseBoard() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter num of rows of the board");
		rows = scan.nextInt();
		
		System.out.println("Enter num of cols of the board");
        cols = scan.nextInt();
        
        max = rows;
        if(cols > rows) {
        		max = cols;
        }
        board = new Cell[max][max];
        
		
        for(int i=0;i<max;++i) {
			for(int j=0;j<max;++j) {
				Cell cell = new Cell("-");
				board[i][j] = cell;
			}
        }
        
		for(int i=0;i<rows;++i) {
			for(int j=0;j<cols;++j) {
				System.out.println("Enter the state at position [" + i + "][" + j + "] ('X'/'-'");
				String s = scan.next();
				while(!isValidCellValue(s)) {
					System.out.println("Enter the state at position [" + i + "][" + j + "] ('X'/'-'");
					s = scan.next();
				}
				board[i][j].setOrgState(s.toUpperCase());
				
			}
		}
		scan.close();
	}
	

	public boolean isValidCellValue(String s) {
		if(s.equalsIgnoreCase("X") || s.equalsIgnoreCase("-")) {
			return true;
		}
		return false;
	}
	
	public void showInputBoard() {
		
		System.out.println("\nInput State of the board : \n");
		for(int i=0;i<rows;++i) {
			for(int j=0;j<cols;++j) {
				String state = board[i][j].getOrgState();
				System.out.print( state + " ");
			}
			System.out.println();
		}
	}
	
	public void showOutputBoard() {
		
		System.out.println("\nOutput State of the board : \n");
		for(int i=0;i<max;++i) {
			for(int j=0;j<max;++j) {
				String state = board[i][j].getOrgState();
				System.out.print( state + " ");
			}
			System.out.println();
		}
	}
	
	public void applyTick() {
		
		Cell [][] outputBoard = new Cell[max][max];
		
		for(int i=0;i<max;++i) {
			for(int j=0;j<max;++j) {
				int liveNeighbours = getLiveNeighbors(i,j);
				String newState = board[i][j].getNewState(liveNeighbours);
				outputBoard[i][j]=new Cell(newState);
			}
		}
		
		board = outputBoard;
	}


	private int getLiveNeighbors(int i, int j) {
		int count = 0;
		for(int m=i-1;m<=i+1;++m ) {
			for(int n=j-1;n<=j+1;++n) {
				if(m>=0 && n>=0 && m<rows && n<cols){
					if(m==i && n==j) {
						continue;
					}
					if(board[m][n].getOrgState().equals("X")) {
						++count;
					}
				}
			}
		}
		return count;
	}
	
}
