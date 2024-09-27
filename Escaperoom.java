import java.util.Random;
import java.util.Scanner;

public class EscapeRoom {

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to EscapeRoom!");
        System.out.println("Type 'help' for game objective and commands.\n");

        GameGUI game = new GameGUI();
        game.createBoard();

        // Initial player position
        int px = 0;
        int py = 0;
        int score = 0;

        // Scanner for user input
        Scanner in = new Scanner(System.in);

        // Valid commands, including teleport
        String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d", 
            "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd", 
            "pickup", "p", "quit", "q", "replay", "help", "?", "ps", "px", "teleport" };

        // Set up the game loop
        boolean play = true;
        while (play) {
            String thing = UserInput.getValidInput(validCommands);
            int m = game.m;  // Move distance for the player

            // Trap detection logic
            if (game.isTrap(60, 0) || game.isTrap(-60, 0) || game.isTrap(0, 60) || game.isTrap(0, -60)) {
                System.out.print("There's a trap nearby! Do you want to spring it? (yes/no): ");
                String springTrap = UserInput.getValidInput(new String[]{"yes", "no"});
                if (springTrap.equals("yes")) {
                    score += game.springTrap(60, 0);
                }
            }

            // Handle different commands
            switch (thing) {
                case "right":
                case "r":
                    score += game.movePlayer(m, 0);
                    System.out.println(score);
                    break;
                case "left":
                case "l":
                    score += game.movePlayer(-m, 0);
                    System.out.println(score);
                    break;
                case "up":
                case "u":
                    score += game.movePlayer(0, -m);
                    System.out.println(score);
                    break;
                case "down":
                case "d":
                    score += game.movePlayer(0, m);
                    System.out.println(score);
                    break;
                case "jump":
                case "jr":
                    if (!game.isTrap(2 * m, 0)) {
                        score += game.movePlayer(2 * m, 0);
                        System.out.println(score);
                    } else {
                        System.out.println("Cannot jump over a trap!");
                    }
                    break;
                case "jumpleft":
                case "jl":
                    if (!game.isTrap(-2 * m, 0)) {
                        score += game.movePlayer(-2 * m, 0);
                        System.out.println(score);
                    } else {
                        System.out.println("Cannot jump over a trap!");
                    }
                    break;
                case "jumpup":
                case "ju":
                    if (!game.isTrap(0, -2 * m)) {
                        score += game.movePlayer(0, -2 * m);
                        System.out.println(score);
                    } else {
                        System.out.println("Cannot jump over a trap!");
                    }
                    break;
                case "jumpdown":
                case "jd":
                    if (!game.isTrap(0, 2 * m)) {
                        score += game.movePlayer(0, 2 * m);
                        System.out.println(score);
                    } else {
                        System.out.println("Cannot jump over a trap!");
                    }
                    break;
                case "pickup":
                case "p":
                    score += game.pickupPrize();
                    System.out.println(score);
                    break;
                case "ps":
                    game.pickupPowerup();
                    System.out.println(score);
                    break;
                case "px":
                    game.cancelpowerup();
                    System.out.println(score);
                    break;

                case "teleport":
                    // Implement teleportation
                    Random rand = new Random();
                    int maxGridX = 600;  // Example grid width (adjust if needed)
                    int maxGridY = 600;  // Example grid height (adjust if needed)
                    
                    px = rand.nextInt(maxGridX);
                    py = rand.nextInt(maxGridY);

                    game.movePlayerTo(px, py);  // Implement movePlayerTo(x, y) in GameGUI
                    System.out.println("You teleported to a new location: (" + px + ", " + py + ")");
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
                    System.out.println("Use jump, jumpup, jumpdown, jumpleft or jr, ju, jd, jl to skip a space");
                    System.out.println("Use pickup, or p, to pick up a coin");
                    System.out.println("Use ps, to pick up a powerup and px to take away its effects");
                    System.out.println("Use teleport to randomly teleport to a new location");
                    System.out.println("Use quit, or q, to end the game once you reach the far right side");
                    System.out.println("Use replay to restart after reaching the far right side.");
                    break;

                default:
                    System.out.println("Invalid input.");
                    score -= 10;
            }
        }

        // End game and print final score and steps
        score += game.endGame();
        System.out.println("Final score = " + score);
        System.out.println("Total steps = " + game.getSteps());
    }
}
