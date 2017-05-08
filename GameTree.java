/*
Josie Maynard and Julia Mini
Jon's Lab
Lab 10 - Hexapawn


Program Design: Constructs the game tree which contains every possible move for both players, alternating whose turn it is
by level of the tree. Class contains methods to check if the game is finished, and to access certain nodes of the tree.
*/
import structure5.*;

class GameTree {

    //initial game board
    protected HexNode root;

    //current state of the game board
    protected HexNode current;

    //number of nodes currently in the tree
    protected int count = 1;
    
    // pre: current board is not null
    // post: constructs a game tree given a current board as the root and the color which indicates which player's turn it is
    public GameTree(HexBoard currentBoard, char color){
	assert(currentBoard != null):("Current board must exist");

	root = new HexNode(currentBoard, color, this);
	current = root;

	//populates the game tree
	constructorHelper(root, color);
    }
	
    // post: constructor that creates the game tree with a new board and the player with white pawns
    public GameTree(){
	this(new HexBoard(), HexBoard.WHITE);
    }

    // pre: current node is not null
    // post: recursive helper that iterates through the possible moves vector and creates a new board and node for each move 
    public void constructorHelper(HexNode currentNode, char color){
	assert(currentNode != null):("Current board must exist");

	//vector of all the possible moves
	Vector<HexMove> moves = currentNode.getBoard().moves(color);

	HexBoard currentBoard = currentNode.getBoard();
	char currentColor = currentNode.getColor();
	
	// base case: if no more moves available or the opponent would win
	if(moves.size()==0 || currentBoard.win(currentBoard.opponent(currentColor))){
	    return;
	} else { //create another level of moves
	    for(int i = 0; i < moves.size(); i++){
		HexNode nextNode =new HexNode(new HexBoard(currentNode.getBoard(), moves.get(i)), currentNode.getBoard().opponent(color), this);
		currentNode.addChild(nextNode);
		constructorHelper(nextNode, currentNode.getBoard().opponent(color));
	    }
	}
    }

    // pre: current node is not null
    // post: returns true if a opponent has won or if there are no possible moves remaining for the current player
    public boolean isFinished(HexNode currentNode){
	assert(currentNode != null):("Current node cannot be null");
	
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
	assert(newCurrent != null):("New current node cannot be null");
	current = newCurrent;
    }

    //pre: count must be positive
    //post: sets the count in the tree
    public void setCount(int count){
	assert(count >= 0):("Count must be positive");
	this.count = count;
    }

    //post: accesses the count in the tree
    public int getCount(){
	return count;
    }
}
