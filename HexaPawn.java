/*
Josie Maynard(+2 design document)
Julia Mini(+2 design document)

Program Design: 
 */

import structure5.*;
import java.util.Scanner;

public class HexaPawn{
    
    public static void main(String [] args){

	String player1 = args[2];
	String player2 = args[3];
	Player firstPlayer;
	Player secondPlayer;
	HexBoard gameBoard = new HexBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	
	GameTree gameTree = new GameTree(gameBoard, HexBoard.WHITE);
	final HexNode root = gameTree.getCurrentNode();
	
	if(player1.equals("human")){
	    firstPlayer = new HumanPlayer(HexBoard.WHITE);
	} else if(player1.equals("computer")){
	    firstPlayer = new ComputerPlayer(HexBoard.WHITE);
	} else { //player 1 is random
	    firstPlayer = new RandomPlayer(HexBoard.WHITE);
	}
	
	if(player2.equals("human")){
	    secondPlayer = new HumanPlayer(HexBoard.BLACK);
	} else if(player2.equals("computer")){
	    secondPlayer = new ComputerPlayer(HexBoard.BLACK);
	} else { //player 2 is random
	    secondPlayer = new RandomPlayer(HexBoard.BLACK);
	}

	playGame(gameTree, root, firstPlayer, secondPlayer);
    }

    public static void playGame(GameTree gameTree, HexNode root, Player firstPlayer, Player secondPlayer){
	Scanner in = new Scanner(System.in);
	
	firstPlayer.play(gameTree, secondPlayer);

	System.out.println("Game Over! Play again? y/n");
	String response = in.next();
	if(response.equals("y")){
	    gameTree.setCurrentNode(root);
	    playGame(gameTree, root, firstPlayer, secondPlayer);
	} else { //response was n
	    return;
	}
    }
	
}
