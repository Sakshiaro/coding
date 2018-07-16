package com.tworks.bootcamp;

/**
 * Game App
 *
 */
public class Game 
{
    public static void main( String[] args )
    {
        
        Board board = new Board();
        board.initialiseBoard();
        board.showInputBoard();
        board.applyTick();
        board.showOutputBoard();
        
        board.applyTick();
        board.showOutputBoard();
        
    }
}
