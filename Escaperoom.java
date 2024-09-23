/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom
{

      // describe the game with brief welcome message
      // determine the size (length and width) a player must move to stay within the grid markings
      // Allow game commands:
      //    right, left, up, down: if you try to go off grid or bump into wall, score decreases
      //    jump over 1 space: you cannot jump over walls
      //    if you land on a trap, spring a trap to increase score: you must first check if there is a trap, if none exists, penalty
      //    pick up prize: score increases, if there is no prize, penalty
      //    help: display all possible commands
      //    end: reach the far right wall, score increase, game ends, if game ended without reaching far right wall, penalty
      //    replay: shows number of player steps and resets the board, you or another player can play the same board
      // Note that you must adjust the score with any method that returns a score
      // Optional: create a custom image for your player use the file player.png on disk
    
      /**** provided code:
      // set up the game
      boolean play = true;
      while (play)
      {
        // get user input and call game methods to play 
        play = false;
      }
      */

  public static void main(String[] args) 
  {      
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60; 
    // individual player moves
    int px = 0;
    int py = 0; 
    
    int score = 0;

    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
    "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
    "pickup", "p", "quit", "q", "replay", "help", "?"};
  
    // set up game
    boolean play = true;
    while (play)
    {
      System.out.print("Enter your movement: ");
      String movement = UserInput.getValidInput(validCommands);
      if (movement.equals("right") || movement.equals("r")) { 
        game.movePlayer(60, 0);
        score++; 
      } 
      if (movement.equals("left") || movement.equals("l")) { 
        game.movePlayer(-60, 0);
        score++; 
      }
      if (movement.equals("up") || movement.equals("u")) { 
        game.movePlayer(0, -60);
        score++; 
      }
      if (movement.equals("down") || movement.equals("d")) { 
          game.movePlayer(0, 60);
          score++; 
      }
      if (movement.equals("quit") || movement.equals("q")) {
        //game.endGame();
        play = false;
      }
      if (movement.equals("pickup") || movement.equals("p")) { 
        game.pickupPrize();
        score++; 
      }
      if (movement.equals("replay")) { 
        game.replay();
      }
      if (movement.equals("jumpup") || movement.equals("ju")) {
        game.movePlayer(0, -120);
        score++; 
      }
      if (movement.equals("jumpright") || movement.equals("jr")) { 
        game.movePlayer(120, 0);
        score++; 
      }
      if (movement.equals("jumpleft") || movement.equals("jl")) {
        game.movePlayer(-120, 0);
        score++; 
      }
      if (movement.equals("jumpdown") || movement.equals("jd")) { 
        game.movePlayer(0, 120);
        score++; 
      }
      if  (movement.equals("help") || movement.equals("?")) { 
        System.out.println("To use these functions you must type the command in the terminal");
        System.out.println("right or r: move the player 1 square right");
        System.out.println("left or l: move the player 1 square left");
        System.out.println("up or u: move the player 1 square up");
        System.out.println("down or d: move the player 1 square down");
        System.out.println("quit or q: quit the game");
        System.out.println("replay: restart the game");
        System.out.println("pickup or p: pickup the prize");
        System.out.println("jumpup or ju: jumps the player 2 squares up");
        System.out.println("jumpdown or jd: jump the player 2 squares down");
        System.out.println("jumpright or jr: jump the player 2 squares right");
        System.out.println("jumpleft or lf: jump the player 2 squares left");
        System.out.println("help or ?: get a list of valid commands");
      }
      if (game.x >430 && game.y>250){
        game.endGame();
        System.out.println("score=" + score);
        System.out.println("steps=" + game.getSteps());
      }
  }

    
  }
}

        
