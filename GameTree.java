/*
Josie Maynard and Julia Mini
Jon's Lab
Lab 10 - Hexapawn


Program Design: Constructs the game tree which contains every possible move for both players, alternating whose turn it is
by level of the tree. Class contains methods to check if the game is finished, and to access certain nodes of the tree.
*/
import structure5.*;

class GameTree {

    protected HexNode root;
    protected HexNode current;
    protected HexNode previous = null; // might be unecessary
    protected static int count = 0;
    
    // pre: current board is not null
    // post: constructs a game tree given a current board as the root and the color which indicates which player's turn it is
    public GameTree(HexBoard currentBoard, char color){
	root = new HexNode(currentBoard, color, this);
	current = root;
	constructorHelper(root, color);
	System.out.println("count: " + count);
    }
	
    // post: constructor that creates the game tree with a new board and the player with white pawns
    public GameTree(){
	this(new HexBoard(), HexBoard.WHITE);
    }

    // pre: current node is not null
    // post: recursive helper that iterates through the possible moves vector and creates a new board and node for each move 
    public void constructorHelper(HexNode currentNode, char color){
	Vector<HexMove> moves = currentNode.getBoard().moves(color);
	HexBoard currentBoard = currentNode.getBoard();
	char currentColor = currentNode.getColor();
	
	// base case: if the moves vector is empty or the game is over, return
	if(moves.size()==0 || currentBoard.win(currentBoard.opponent(currentColor))){
	    return;
	} else {
	    for(int i = 0; i < moves.size(); i++){
		HexNode nextNode =new HexNode(new HexBoard(currentNode.getBoard(), moves.get(i)), currentNode.getBoard().opponent(color), this);
		currentNode.addChild(nextNode);
		count++;
		//System.out.println(nextNode.getBoard().toString());
		constructorHelper(nextNode, currentNode.getBoard().opponent(color));
	    }
	}
    }

    // pre: current node is not null
    // post: returns true if a player has won or if there are no possible moves remaining
    public boolean isFinished(HexNode currentNode){
	HexBoard currentBoard = currentNode.getBoard();
	char currentColor = currentNode.getColor();
        return currentBoard.win(currentBoard.opponent(currentColor)) ||
	    currentNode.childrenSize() == 0;
    }

    // post: return the current node
    public HexNode getCurrentNode(){
	return current;
    }

    // pre: new current node is not null
    // post: sets current node 
    public void setCurrentNode(HexNode newCurrent){
	current = newCurrent;
    }

    //might be unnecessary
    // post: returns the parent of the current node
    public HexNode getPreviousNode(){
	return previous;
    }

    //might be unnecessary
    // pre: new previous node is not null
    // post: sets the part of the current node
    public void setPreviousNode(HexNode newPrevious){
	previous = newPrevious;
    }
    
    public static void main(String[] args){
	new GameTree();
    }

    
}
