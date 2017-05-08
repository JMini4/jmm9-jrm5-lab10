/*

Program Description:
*/
import structure5.*;

public class HumanPlayer implements Player{

    private char color;
    
    public HumanPlayer(char color){
        this.color = color;
    }

    public Player play(GameTree tree, Player opponent){

        HexNode currentNode = tree.getCurrentNode();
	ReadStream r = new ReadStream();
	System.out.println(currentNode.getBoard().toString());
	
        if(tree.isFinished(currentNode)){
	    
	    if(currentNode.getBoard().win(currentNode.getColor())){
		System.out.println("Human Player won!");
		return this;
            } else {
		System.out.println("Opponent1 won!");
                return opponent;
            }
        } else{
	    Vector<HexMove> moves = currentNode.getBoard().moves(currentNode.getColor());
	    int i;
	    for(i = 0; i < moves.size(); i++){
		int number = i + 1;
		System.out.println(number + ". : " + moves.get(i).toString());
	    }
	    System.out.println((i+1) + ". Resign?"); 
	    System.out.println("Pick your move! <3");
	    // assert !r.eof()
	    int choice = r.readInt();
	    if(choice == i + 1){
		return opponent;
	    }
	    currentNode = currentNode.getChild(choice - 1);
	    tree.setCurrentNode(currentNode);
	    return opponent.play(currentNode.getTree(), this);
        }
    }
}
