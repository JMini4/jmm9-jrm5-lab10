/*
Josie Maynard and Julia Mini
Jon's Lab
Lab 10 - HexaPawn

Class that constructs a node in our game tree and allows us to add, remove and access the nodes and their children
The class also contains method to access the tree for a given node 
*/
import structure5.*;
import java.util.Iterator;

public class HexNode{

    //structure to hold the successive moves that are children of the current node
    Vector<HexNode> children;
    
    HexBoard gameBoard;
    char color;
    GameTree tree;
    
    // pre: board, color, and tree are not null
    // post: constructs a node from a given game board, color of pawn, and tree
    public HexNode(HexBoard board, char color, GameTree tree){
	gameBoard = board;
	this.color = color;
	this.tree = tree;
	children = new Vector<HexNode>();
    }

    // pre: new game board is not null
    // post: adds a new game board to the tree by adding the board to the vector of children
    // updates the count in the tree
    public void addChild(HexNode newGameBoard){
	assert(newGameBoard != null):("New node cannot be null");
	children.add(newGameBoard);
	tree.setCount(tree.getCount()+1);
    }

    // pre: game board is not null
    // post: removes a child from a node
    //updates the count in the tree
    public void removeChild(HexNode gameBoard){
	assert(gameBoard != null):("Game board cannot be null");
	children.remove(gameBoard);
	tree.setCount(tree.getCount()-1);
    }

    // post: returns the board for the node
    public HexBoard getBoard(){
	return gameBoard;
    }

    // post: returns the color for the node      
    public char getColor(){
	return color;
    }

    //pre: numberChoice is not negative
    //post: returns the child node at the given index
    public HexNode getChild(int numberChoice){
	assert(numberChoice >= 0):("Number choice cannot be negative");
	return children.get(numberChoice);
    }

    //post: returns the tree for the node
    public GameTree getTree(){
	return tree;
    }

    //post: returms the number of children for a given node
    public int childrenSize(){
	return children.size();
    }
}

