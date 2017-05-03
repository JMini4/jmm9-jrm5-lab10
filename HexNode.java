
public class HexNode{

    Vector<HexNode> children;
    HexBoard gameBoard;
    char color;

    public HexNode(HexBoard board, char color){
	gameBoard = board;
	this.color = color;
	children = new Vector<HexNode>();

    }

    public addChild(HexNode newGameBoard){
	children.add(newGameBoard);
    }

    public removeChild(HexNode gameBoard){
	children.remove(gameBoard);
    }

    public iterator(){
	children.iterator();
    }

    
}
