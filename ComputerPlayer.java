/*
Josie Maynard and Julia Mini
Jon's Lab
Lab 10 - HexaPawn
 */

// Class that simulates a computer player in the game Hexapawn. The computer player can trim the game tree so
// that it learns what moves lead to a win.

import structure5.*;
import java.util.Random;

public class ComputerPlayer implements Player{

    private char color;
    private boolean trimming = false;
    
    // make sure your constructor accepts a char (HexBoard.WHITE or
    // HexBoard.BLACK) to play with.  This should be remembered.
    public ComputerPlayer(char color){
	this.color = color;
    }

    // post: recursively switches between players chooisng moves until one of them has won the game
    public Player play(GameTree tree, Player opponent){

	Random moveChoice = new Random();
	HexNode currentNode = tree.getCurrentNode();
	
	// base case: if the game is over, return who won
	if(tree.isFinished(currentNode)){
	    if(currentNode.getBoard().win(currentNode.getColor())){
		return this;
	    } else {
		trimming = true; //looking to trim
		return opponent;
	    }
	} else{ 
	    //tree.setPreviousNode(currentNode());
	    HexNode newNode = currentNode.getChild(moveChoice.nextInt(currentNode.getBoard().moves(currentNode.getColor()).size()));
	    tree.setCurrentNode(newNode);
	    Player winner = opponent.play(newNode.getTree(), this);
	    if(winner == opponent && trimming == true){
		currentNode.removeChild(newNode); //is this going to cause problems since wemay have updated current node- when does recursive call happen?
		trimming = false; //already trimmed
	    }
	    return winner; 
	}
	
    }
}
