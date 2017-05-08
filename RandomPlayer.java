import structure5.*;
import java.util.Random;
/*
Program Description:
 */
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
	    System.out.println(currentNode.getBoard().toString());
	    if(currentNode.getBoard().win(currentNode.getColor())){
		System.out.println("Random Player won!");
		return this;
	    } else {
		System.out.println("Opponent2 won!");
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
