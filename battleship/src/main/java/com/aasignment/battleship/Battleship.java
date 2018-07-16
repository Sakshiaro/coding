/**
 * Battle Ship Game
 * Number of players - 2
 */

package com.aasignment.battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Battleship {

	private Board p1Board;
	private Board p2Board;
	private ArrayList<String> p1Missiles;
	private ArrayList<String> p2Missiles;
	private String currentTurn;
	
	public String getCurrentTurn() {
		return currentTurn;
	}


	public void setCurrentTurn(String currentTurn) {
		this.currentTurn = currentTurn;
	}


	void playGame() {
		
		while((!p1Missiles.isEmpty()) || (!p2Missiles.isEmpty())) {
			
			if(p1Missiles.isEmpty() && currentTurn.equalsIgnoreCase("p1")) {
				System.out.println("Player-1 has no more missiles left to launch");
				this.currentTurn = "P2";
			}
			
			if(p2Missiles.isEmpty() && currentTurn.equalsIgnoreCase("p2")) {
				System.out.println("Player-2 has no more missiles left to launch");
				this.currentTurn = "P1";
			}
			
			if(currentTurn.equalsIgnoreCase("p1") && (!p1Missiles.isEmpty())) {
				String missileLocation = p1Missiles.remove(0);
				String result = p2Board.fireMissile(missileLocation);
				if(result.equals("miss")){
					currentTurn = "P2";
				}
				System.out.println("Player-1 fires a missile with target " + missileLocation + " which got " + result);
			}
			else if(currentTurn.equalsIgnoreCase("p2") && (!p2Missiles.isEmpty())) {
				String missileLocation = p2Missiles.remove(0);
				String result = p1Board.fireMissile(missileLocation);
				if(result.equals("miss")){
					currentTurn = "P1";
				}
				System.out.println("Player-2 fires a missile with target " + missileLocation + " which got " + result);
			}
			
			if(p1Board.allShipsDestroyed()) {
				System.out.println("Player-2 won the Battle");
				return;
				
			}
			else if(p2Board.allShipsDestroyed()) {
				System.out.println("Player-1 won the Battle");
				return;
			}		
		}
		
		
		if(p1Missiles.isEmpty() && p2Missiles.isEmpty()) {
			if(!p1Board.allShipsDestroyed() && !p2Board.allShipsDestroyed()) {
				System.out.println("Player-1 and Player-2 declare peace");
			}
		}
	}
	
	Boolean validateRange(String line, int min, int max) {
		if(!line.matches("^\\d+$")){
			return false;
		}
		int convertedNum = Integer.parseInt(line);
		if(convertedNum >= min && convertedNum<=max) {
			return true;
		}
		return false;
	}
	
	
	void initGame() {
		String line;
		Scanner inputline = new Scanner(System.in);
        System.out.println("\nEnter the width[1-9] and height[A-Z] (separated by spaces)" );
        line = inputline.nextLine();
        while(!line.matches("^[1-9] [A-Z]$"))  
        {  
        	System.out.println("Invalid input");
        	System.out.println("\nEnter the width[1-9] and height[A-Z] (separated by spaces)" );
        	line = inputline.nextLine(); 
        }
        
        String[] parts = line.split(" ");
        int width = Integer.parseInt(parts[0]);
        char height = parts[1].charAt(0);
        
        p1Board = new Board("P1", width, height);
        p1Board.initBoard();
        
        p2Board = new Board("P2", width, height);
        p2Board.initBoard();
        
        System.out.println("\nEnter the number of Ships");
        line = inputline.nextLine();
        
        int calculatedHeight = (height - 'A') +1;
        int maxShips = width * calculatedHeight;
        while(!validateRange(line, 1, maxShips))  
        {  
        	System.out.println("Invalid input");
        	System.out.println("\nEnter the number of Ships");
        	line = inputline.nextLine(); 
        }
        
        int numShips = Integer.parseInt(line);
        int counter=0;
        
        String widthPattern = "[1-" + width + "]";
    	String heightPattern = "[1-" + calculatedHeight + "]";
    	char maxHeightAlphabet = (char) ('A' + (calculatedHeight-1));
    	String missileLocPattern = "[A-" + maxHeightAlphabet + "][1-" + width + "]";
    	String shipPattern = "^(P|Q) " + widthPattern + " " + heightPattern + " " + missileLocPattern + " " + missileLocPattern;
    	
        while(counter != numShips) {
        	++counter;
        	
        	
        	Boolean notValidated= true;
        	
        	while(notValidated) {
        		System.out.println("\nFor Ship-" + counter+ ": Enter the ShipType, ShipWidth, ShipHeight, Player1coordinate and Player2Coordinate (separated by spaces)");
        		line = inputline.nextLine();
        		if(!line.matches(shipPattern)) {
        			System.out.println("Invalid input for Ship-" + counter);
            		System.out.println("\nFor Ship-" + counter+ ": Enter the ShipType, ShipWidth, ShipHeight, Player1coordinate and Player2Coordinate (separated by spaces)");
                	line = inputline.nextLine();
                	continue;
        		}
        		
        		parts = line.split(" ");
            	char shipType = parts[0].charAt(0);
            	int shipWidth = Integer.parseInt(parts[1]);
            	int shipHeight = Integer.parseInt(parts[2]);
            	String P1pos = parts[3];
            	String P2pos = parts[4];
            	
            	if(p1Board.isPossiblePlacement("P1", shipType, shipWidth, shipHeight, P1pos) && p2Board.isPossiblePlacement("P2", shipType, shipWidth, shipHeight, P2pos)) {
            		p1Board.placeShip(shipType, shipWidth, shipHeight, P1pos);
                	p2Board.placeShip(shipType, shipWidth, shipHeight, P2pos);
            		notValidated = false;
            		break;
            	}
            	
            	
        	}
        	
        }
        
        // draws the board for both the players
        getPlayersStatus();
        
        String locationArrayPattern = "^" + missileLocPattern + "( " + missileLocPattern+ ")*$"; 
        System.out.println("\nEnter the missile positions fired by P1 (separated by spaces)");
        line = inputline.nextLine();
        while(!line.matches(locationArrayPattern)){
        	System.out.println("Invalid input for P1 missiles");
        	System.out.println("\nEnter the missile positions fired by P1 (separated by spaces)");
        	line = inputline.nextLine();
        }
        p1Missiles = new ArrayList<String>(Arrays.asList(line.split(" ")));
        
        System.out.println("\nEnter the missile positions fired by P2 (separated by spaces)");
        while(!line.matches(locationArrayPattern)){
        	System.out.println("Invalid input for P2 missiles");
        	System.out.println("\nEnter the missile positions fired by P2 (separated by spaces)");
        	line = inputline.nextLine();
        }
        line = inputline.nextLine();
        p2Missiles = new ArrayList<String>(Arrays.asList(line.split(" ")));
	}
	
	void getPlayersStatus() {
		System.out.println("\n\nBoard for P1 :\n");
        p1Board.displayBoard();
        
        System.out.println("\n\nBoard for P2 :\n");
        p2Board.displayBoard();
        
	}
	public static void main( String[] args )
    {
        Battleship battleShip = new Battleship();
        battleShip.initGame();
        battleShip.setCurrentTurn("P1");
        battleShip.playGame();
    }
}
