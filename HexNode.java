/*
Josie Maynard and Julia Mini
Jon's Lab
Lab 10 - HexaPawn

Class that constructs a node in our game tree and allows us to add remove and access the nodes and their children
The class also contaisn methods for iterating over the nodes and accessing the tree for a given node 
*/
import structure5.*;
import java.util.Iterator;

public class HexNode{

    Vector<HexNode> children;
    HexBoard gameBoard;
    char color;
    GameTree tree;
    
    // pre: board, color, and tree are not null
    // post: constructs a node from a given game board, color of pawn, and tree
    public HexNode(HexBoard board, char color, GameTree tree){
	gameBoard = board;
	this.color = color;
	children = new Vector<HexNode>();
	this.tree = tree;
    }

    // pre: new game board is not null
    // post: adds a new game board to the tree by adding the moard to the vector of children
    public void addChild(HexNode newGameBoard){
	children.add(newGameBoard);
    }

    // pre: game board is not null
    // post: 
    public void removeChild(HexNode gameBoard){
	children.remove(gameBoard);
    }

    public Iterator<HexNode> iterator(){
	return children.iterator();
    }

    public HexBoard getBoard(){
	return gameBoard;
    }

    public char getColor(){
	return color;
    }

    public HexNode getChild(int numberChoice){
	return children.get(numberChoice);
    }

    public GameTree getTree(){
	return tree;
    }

    public int childrenSize(){
	return children.size();
    }
}
