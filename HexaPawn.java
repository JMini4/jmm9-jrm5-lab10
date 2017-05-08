/*
Josie Maynard(+2 design document)
Julia Mini(+2 design document)
Jon's Lab
Lab 10- HexaPawn

Thought Questions:
1. There are 6003 board positions for the 3x4 board (this is how many nodes are originally in the tree). 
There are 215150 board positions for the 3x5 board. If we tried to find out how many positions are in 
a bigger board, the program might crash due to memory allocation. Also, these positions may not be unique
if there are multiple moves that lead to the same game board.

2. In the 3x3 board, the second player will win much more frequently. When we tested it, out of 1000 games, player 1 won 13 games, while player 2 won 987 games. This is because, once the computer for the second player has avoided moves that led to a loss, it is easy for it to trap the first player on the small board during the second player's first move. In the 3x4 board, however, the first player won more frequently (out of 1000 games, player 1 won 921 games and player 2 won 79 games). This is because, due to the wider board, the second player cannot trap the first player during the second player's first move, allowing the first player to gain an advantage. On the other hand, in the 4x3 board, the second player wins more frequently (out of 1000 games, the first player won 117 games and the second player won 883 games). This means the second player can trap the first player during their second move, similar to the 3x3 board, for the extra length did not give player 1 an advantage. In the 3x5 board, out of 1000 games, the first player won 727 games, while the second player won 273 games. Again, the wider board helped the first player because they weren't as easily trapped. Lastly, in the 5x3 board, out of 1000 games, the first player won 590 games, while the second player won 410. Overall, as the size of the board gets bigger, the likelihood of each player winning approaches 50%. 

Program Design: Creates a HexaPawn game that consists of either human, computer, or random players.
Users can choose to keep playing or quit the game.
 */

import structure5.*;
import java.util.Scanner;

public class HexaPawn{

    private static int player1Count = 0;
    private static int player2Count = 0;
    
    public static void main(String [] args){

	assert(args.length == 4):("Enter 4 commands to play the game");
	assert(Integer.parseInt(args[0]) >= 3 && Integer.parseInt(args[1]) >= 3):("Game board dimensions must be greater or equal to 3x3");
	assert((args[2].equals("human") || args[2].equals("computer") || args[2].equals("random")) &&
	       (args[3].equals("human") || args[3].equals("computer") || args[3].equals("random"))):
	       ("You must enter either a human, computer, or random player");

	String player1 = args[2];
	String player2 = args[3];
	Player firstPlayer;
	Player secondPlayer;

	//creates the initial game board of the desired size
	HexBoard gameBoard = new HexBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	
	GameTree gameTree = new GameTree(gameBoard, HexBoard.WHITE);

	//to remember the initial gameBoard
	final HexNode root = gameTree.getCurrentNode();

	//create the first player
	if(player1.equals("human")){
	    firstPlayer = new HumanPlayer(HexBoard.WHITE);
	} else if(player1.equals("computer")){
	    firstPlayer = new ComputerPlayer(HexBoard.WHITE);
	} else { //player 1 is random
	    firstPlayer = new RandomPlayer(HexBoard.WHITE);
	}

	//create the second player
	if(player2.equals("human")){
	    secondPlayer = new HumanPlayer(HexBoard.BLACK);
	} else if(player2.equals("computer")){
	    secondPlayer = new ComputerPlayer(HexBoard.BLACK);
	} else { //player 2 is random
	    secondPlayer = new RandomPlayer(HexBoard.BLACK);
	}

	//simulates the game if a human is not playing
	if(!player1.equals("human") && !player2.equals("human")){
	    simulateGame(gameTree, root, firstPlayer, secondPlayer);
	} else {
	    playGame(gameTree, root, firstPlayer, secondPlayer);
	}
    }

    //pre: gameTree, root, firstPlayer, and secondPlayer are not null
    //post: plays the game until the user doesn't want to play anymore
    public static void playGame(GameTree gameTree, HexNode root, Player firstPlayer, Player secondPlayer){
	//to read in whether the user wants to keep playing
	Scanner in = new Scanner(System.in);

	//display who won
	Player winner = firstPlayer.play(gameTree, secondPlayer);
	if(winner == firstPlayer){
	    System.out.println("Player 1 won!");
	} else {
	    System.out.println("Player 2 won!");
	}

	//display the number of nodes in the tree
	//used for testing
	System.out.println(gameTree.getCount());
	
	System.out.println("Game Over! Play again? y/n");
	String response = in.next();

	if(response.equals("y")){
	    //reset the board to the initial state, but maintain the tree and play again
	    gameTree.setCurrentNode(root);
	    playGame(gameTree, root, firstPlayer, secondPlayer);
	} else { //response was n, exit the game
	    return;
	}
    }

    //pre: gameTree and root are not null, player1 and player2 are either random or computer players
    //post: simulates 1000 games between two non-human players
    public static void simulateGame(GameTree gameTree, HexNode root, Player player1, Player player2){
	for(int i = 0; i < 1000; i++){
	    Player winner = player1.play(gameTree, player2);
	    if(winner == player1){
		player1Count++;
	    } else {
		player2Count++;
	    }
	    //reset to the initial game board
	    gameTree.setCurrentNode(root);
	}
	System.out.println("Player 1 wins: " + player1Count);
	System.out.println("Player 2 wins: " + player2Count);
    }
    
	
}
