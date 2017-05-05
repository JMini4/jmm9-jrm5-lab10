import structure5.*;
import java.util.Random;

public class ComputerPlayer implements Player{

    private char color;
    private boolean trimming;
    
    // make sure your constructor accepts a char (HexBoard.WHITE or
    // HexBoard.BLACK) to play with.  This should be remembered.
    public ComputerPlayer(char color){
	this.color = color;
    }

    public Player play(GameTree tree, Player opponent){

	Random moveChoice = new Random();
	HexNode currentNode = tree.getCurrentNode();
	
	if(tree.isFinished(currentNode)){
	    if(currentNode.getBoard().win(currentNode.getColor())){
		return this;
	    } else {
		trimming = true;
		return opponent;
	    }
	} else{ 
	    //tree.setPreviousNode(currentNode());
	    HexNode newNode = currentNode.getChild(moveChoice.nextInt(currentNode.getBoard().moves(currentNode.getColor()).size()));
	    tree.setCurrentNode(newNode);
	    Player winner = opponent.play(newNode.getTree(), this);
	    if(winner == opponent && trimming == true){
		currentNode.removeChild(newNode); //is this going to cause problems since wemay have updated current node- when does recursive call happen?
		trimming = false;
	    }
	    return winner; 
	}
	
    }
}
