

import structure5.*;

class GameTree {

    HexNode root;
    HexNode current;
    HexNode previous = null; // might be unecessary
    
    public GameTree(HexBoard currentBoard, char color){
	root = new HexNode(currentBoard, color, this);
	current = root;
	constructorHelper(root, color);
    }
	
    public GameTree(){
	this(new HexBoard(), HexBoard.WHITE);
    }

    public void constructorHelper(HexNode currentNode, char color){
	Vector<HexMove> moves = currentNode.getBoard().moves(color);
	if(moves.size()==0 || isFinished(currentNode)){
	    return;
	} else {
	    for(int i = 0; i < moves.size(); i++){
		HexNode nextNode =new HexNode(new HexBoard(currentNode.getBoard(), moves.get(i)), currentNode.getBoard().opponent(color), this);
		currentNode.addChild(nextNode);
		System.out.println(nextNode.getBoard().toString());
		constructorHelper(nextNode, currentNode.getBoard().opponent(color));
	    }
	}
    }

    public boolean isFinished(HexNode currentNode){
        return currentNode.getBoard().win(currentNode.getColor()) ||
	    currentNode.getBoard().win(currentNode.getBoard().opponent(currentNode.getColor())) ||
	    currentNode.childrenSize() == 0;
    }

    public HexNode getCurrentNode(){
	return current;
    }

    public void setCurrentNode(HexNode newCurrent){
	current = newCurrent;
    }
    //might be unnecessary
    public HexNode getPreviousNode(){
	return previous;
    }

    //might be unnecessary
    public void setPreviousNode(HexNode newPrevious){
	previous = newPrevious;
    }
    
    public static void main(String[] args){
	new GameTree();
    }

    
}
