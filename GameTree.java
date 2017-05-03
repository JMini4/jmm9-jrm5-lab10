
import structure5.*;

class GameTree {

    HexNode root;

    public GameTree(HexBoard currentBoard, char color){
	root = new HexNode(currentBoard, color);
        this.constructorHelper(currentBoard, color);
	while(root.iterator().hasNext()){
	    HexBoard nextBoard = root.iterator().next().getBoard();
	    constructorHelper(nextBoard, nextBoard.opponent(color) );
	}
    }
	


    public GameTree(){
	this(new HexBoard(), HexBoard.WHITE);
    }

    public void constructorHelper(HexBoard currentBoard, char color){
	Vector<HexMoves> moves = currentBoard.moves();
	for(int i; i < moves.size(); i++){
	    HexNode nextBoard =new HexNode(new HexBoard(currentBoard, moves.get(i)), currentBoard.opponent(color));
	    currentBoard.addChild(nextBoard);
	}
    }

    
}
