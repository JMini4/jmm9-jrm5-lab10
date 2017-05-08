/*
Josie Maynard and Julia Mini
Jon's Lab
Lab 10 - HexaPawn

Program description: Class that simulates a computer player in the game Hexapawn. The computer player can trim the game tree so
that it avoids moves that previously led it to a loss.
*/
import structure5.*;
import java.util.Random;

public class ComputerPlayer implements Player{

    private char color;

    //keeps track whether it needs to remove a node that led to a loss
    private boolean trimming = false;
    
    //pre: color has to be either HexBoard.WHITE or HexBoard.BLACK
    //post: constructs a human player with a specific color
    public ComputerPlayer(char color){
	assert(color == HexBoard.WHITE || color == HexBoard.BLACK):("Color must be white or black");
	this.color = color;
    }

      
    //pre: tree and opponent are not null
    //post: plays the game by picking moves randomly and removes moves that led to a loss
    public Player play(GameTree tree, Player opponent){

	//to randomly pick a move
	Random moveChoice = new Random();
	
	HexNode currentNode = tree.getCurrentNode();
	
	if(tree.isFinished(currentNode)){ //the opponent won in the most recent move   
	    trimming = true; //looking to trim 
	    return opponent;
	} else{ //complete the next move
	    //choose a random move in the remaining children and update the tree
	    HexNode newNode = currentNode.getChild(moveChoice.nextInt(currentNode.childrenSize()));
	    tree.setCurrentNode(newNode);

	    //see if the opponent won and remove the node if we need to trim
	    Player winner = opponent.play(newNode.getTree(), this);
	    if(winner == opponent && trimming == true){
		currentNode.removeChild(newNode); 
		trimming = false; //already trimmed
	    }
	    return winner; 
	}
    }
}
