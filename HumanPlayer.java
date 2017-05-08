/*
Josie Maynard and Julia Mini                                                                                                                                                              
Jon's Lab                                                                                                                                                                                 
Lab 10 - HexaPawn

Program Description: Creates a interactive way of playing Hexapawn in which a human enters their desired moves.
*/
import structure5.*;

public class HumanPlayer implements Player{

    private char color;

    //pre: color has to be either HexBoard.WHITE or HexBoard.BLACK
    //post: constructs a human player with a specific color
    public HumanPlayer(char color){
	assert(color == HexBoard.WHITE || color == HexBoard.BLACK):("Color must be white or black");
        this.color = color;
    }

    //pre: tree and opponent are not null
    //post: plays the game interactively for a human
    public Player play(GameTree tree, Player opponent){
	
        HexNode currentNode = tree.getCurrentNode();

	//to read in human input
	ReadStream r = new ReadStream();
	
	System.out.println(currentNode.getBoard().toString());

	if(tree.isFinished(currentNode)){ //the opponent won in the most recent move
	    return opponent;
	} else { //current player makes a move
	    //prints out possible moves
	    Vector<HexMove> moves = currentNode.getBoard().moves(currentNode.getColor());
	    int i;
	    for(i = 0; i < moves.size(); i++){
		System.out.println((i+1) + ". : " + moves.get(i).toString());
	    }
	    System.out.println((i+1) + ". Resign?"); 
	    System.out.println("Pick your move! <3");

	    int choice = r.readInt();
	    if(choice == i + 1){ //user chose resign
		return opponent;
	    }

	    //complete the move and update the tree
	    currentNode = currentNode.getChild(choice - 1);
	    tree.setCurrentNode(currentNode);
	    return opponent.play(currentNode.getTree(), this);
	}
    }
}
