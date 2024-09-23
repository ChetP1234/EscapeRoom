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
    System.out.println("Type 'help' for game objective and commands. \n");

    
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
    
      /* TODO: get all the commands working */
      /* Your code here */
      String thing = UserInput.getValidInput(validCommands);
      if (game.isTrap(60, 0) || game.isTrap(-60, 0) || game.isTrap(0, 60) || game.isTrap(0, -60)) {
        System.out.print("There's a trap nearby! Do you want to spring it? (yes/no): ");
        String springTrap = UserInput.getValidInput(new String[]{"yes", "no"});
        if (springTrap.equals("yes")) {
            score += game.springTrap(60, 0);
        }
      }

      switch (thing) {
        case "right":
        case "r":
            score += game.movePlayer(m, 0);
            break;
        case "left":
        case "l":
            score += game.movePlayer(-m, 0);
            break;
        case "up":
        case "u":
            score += game.movePlayer(0, -m);
            break;
        case "down":
        case "d":
            score += game.movePlayer(0, m);
            break;
        case "jump":
        case "jr":
            if (!game.isTrap(2*m, 0)) {
                score += game.movePlayer(2*m, 0);
            } else {
                System.out.println("Cannot jump over a trap!");
            }
            break;
        case "jumpleft":
        case "jl":
            if (!game.isTrap(-2*m, 0)) {
                score += game.movePlayer(-2*m, 0);
            } else {
                System.out.println("Cannot jump over a trap!");
            }
            break;
        case "jumpup":
        case "ju":
            if (!game.isTrap(0, -2*m)) {
                score += game.movePlayer(0, -2*m);
            } else {
                System.out.println("Cannot jump over a trap!");
            }
            break;
        case "jumpdown":
        case "jd":
            if (!game.isTrap(0, 2*m)) {
                score += game.movePlayer(0, 2*m);
            } else {
                System.out.println("Cannot jump over a trap!");
            }
            break;
        case "pickup":
        case "p":
            score += game.pickupPrize();
            break;
        case "quit":
        case "q":
            play = false;
            break;
        case "replay":
            score += game.replay();
            break;
        case "help":
        case "?":
          System.out.println("\nWelcome to our Escape Game!");
          System.out.println("Objective: pick up all coins and escape to the far right");
          System.out.println("Use up, down, left, right, or u, d, l, r, to move");
          System.out.println("use jump, jumpup, jumpdown, jumpleft or jr, ju, jd, jl to skip a space");
          System.out.println("Use pickup, or p, to pickup a coin");
          System.out.println("Use quit, or q, to end the game once you reach the far right side");
          System.out.println("Use replay to restart after reaching the far right side.");
            break;
        default:
            System.out.println("Invalid input.");
            score -= 10;
        
      }
      
    }

    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}
