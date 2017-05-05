
import structure5.*;
import java.util.Iterator;

public class HexNode{

    Vector<HexNode> children;
    HexBoard gameBoard;
    char color;
    GameTree tree;
    
    public HexNode(HexBoard board, char color, GameTree tree){
	gameBoard = board;
	this.color = color;
	children = new Vector<HexNode>();
	this.tree = tree;
    }

    public void addChild(HexNode newGameBoard){
	children.add(newGameBoard);
    }

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
