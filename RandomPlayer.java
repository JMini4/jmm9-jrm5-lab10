import structure5.*;
import java.util.Random;
/*
Josie Maynard and Julia Mini                                                                                                                                                              
Jon's Lab                                                                                                                                                                                 
Lab 10 - HexaPawn  

Program Description: Creates a player for HexaPawn that chooses its moves randomly.
 */
public class RandomPlayer implements Player{

    private char color;

    //pre: color has to be either HexBoard.WHITE or HexBoard.BLACK
    //post: constructs a random player with a specific color  
    public RandomPlayer(char color){
	assert(color == HexBoard.WHITE || color == HexBoard.BLACK):("Color must be white or black");
	this.color = color;
    }

    //pre: tree and opponent are not null
    //post: plays the game by picking moves randomly
    public Player play(GameTree tree, Player opponent){

	//to randomly pick a move
	Random moveChoice = new Random();
	
	HexNode currentNode = tree.getCurrentNode();

	if(tree.isFinished(currentNode)){  //the opponent won in the most recent move    
	    return opponent;
	} else{ //complete the random move and update the tree
	    currentNode = currentNode.getChild(moveChoice.nextInt(currentNode.getBoard().moves(currentNode.getColor()).size()));
	    tree.setCurrentNode(currentNode);
	    return opponent.play(currentNode.getTree(), this);
	}
    }
}
