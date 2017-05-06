
import structure5.*;

public class HumanPlayer implements Player{
    public HumanPlayer(char color){
        this.color = color;
    }

    public Player play(GameTree tree, Player opponent){

        HexNode currentNode = tree.getCurrentNode();
	ReadStream r = new ReadStream();

        if(tree.isFinished(currentNode)){
            if(currentNode.getBoard().win(currentNode.getColor())){
                return this;
            } else {
                return opponent;
            }
        } else{
	    Vector<HexMove> moves = currentNode.getBoard().moves(currentNode.getColor());
	    for(int i = 0; i < moves.size(); i++){
		int number = i + 1;
		System.out.println(number + ". : " + moves.get(i).toString());
	    }
	    System.out.println("Pick your move! <3");
	    if(!r.eof()){
		int choice = r.readInt();
		currentNode = currentNode.getChild(choice - 1);
		tree.setCurrentNode(currentNode);
		return opponent.play(currentNode.getTree(), this);
	    }
        }
    }
}
