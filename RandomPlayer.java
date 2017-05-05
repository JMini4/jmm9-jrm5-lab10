import structure5.*;
import java.util.Random;

public class RandomPlayer implements Player{

    private char color;
    
    // make sure your constructor accepts a char (HexBoard.WHITE or
    // HexBoard.BLACK) to play with.  This should be remembered.  
    public RandomPlayer(char color){
	this.color = color;
    }
    
    public Player play(GameTree tree, Player opponent){

	Random moveChoice = new Random();
	HexNode currentNode = tree.getCurrentNode();
	
	if(tree.isFinished(currentNode)){
	    if(currentNode.getBoard().win(currentNode.getColor())){
		return this;
	    } else {
		return opponent;
	    }
	} else{
	    //tree.setPreviousNode(currentNode);
	    currentNode = currentNode.getChild(moveChoice.nextInt(currentNode.getBoard().moves(currentNode.getColor()).size()));
	    tree.setCurrentNode(currentNode);
	    return opponent.play(currentNode.getTree(), this);
	}
    }
}
