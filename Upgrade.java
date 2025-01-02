/**
 * Kylie Gun
 * 400524717
 * McMaster University 
 * December 4, 2024
 * 2OP3 - Object Oriented Programming Fall 2024
 */

import A6_P1.TicTacToe.Board;
import A6_P1.TicTacToe.Player;
import A6_P1.TicTacToe.HumanPlayer;
import A6_P1.TicTacToe.ComputerPlayer;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Upgrade class extends the functionality of TicTacToe to include a dynamic grid size (N x N)
 * and a customizable winning condition (M symbols in a row).
 */
public class Upgrade {
    private final Board board; // The game board
    private final Player player1; // First player
    private final Player player2; // Second player
    private final int N; // Size of the grid
    private final int M; // Number of consecutive symbols required to win

    /**
     * Constructor for the Upgrade class.
     * 
     * @param player1 First player
     * @param player2 Second player
     * @param N Size of the grid (NxN)
     * @param M Winning condition (M consecutive symbols)
     */
    public Upgrade(Player player1, Player player2, int N, int M) {
        this.N = N;
        this.M = M;
        this.player1 = player1;
        this.player2 = player2;

        // Initialize the game board with a dynamic size
        this.board = new Board();
        char[][] dynamicGrid = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dynamicGrid[i][j] = ' '; // Set each cell to empty
            }
        }
        board.setGrid(dynamicGrid); // Update the board with the new grid
    }

    /**
     * Checks if the given symbol has achieved a winning condition.
     * 
     * @param symbol The symbol to check for victory
     * @return True if the symbol satisfies the winning condition, false otherwise
     */
    private boolean checkWinner(char symbol) {
        char[][] grid = board.getGrid();

        // Check rows for M consecutive symbols
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
                boolean win = true;
                for (int k = 0; k < M; k++) {
                    if (grid[i][j + k] != symbol) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }

        // Check columns for M consecutive symbols
        for (int i = 0; i <= N - M; i++) {
            for (int j = 0; j < N; j++) {
                boolean win = true;
                for (int k = 0; k < M; k++) {
                    if (grid[i + k][j] != symbol) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int i = 0; i <= N - M; i++) {
            for (int j = 0; j <= N - M; j++) {
                boolean win = true;
                for (int k = 0; k < M; k++) {
                    if (grid[i + k][j + k] != symbol) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }

        // Check diagonals (top-right to bottom-left)
        for (int i = 0; i <= N - M; i++) {
            for (int j = M - 1; j < N; j++) {
                boolean win = true;
                for (int k = 0; k < M; k++) {
                    if (grid[i + k][j - k] != symbol) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }

        // No win condition met
        return false;
    }

    /**
     * Starts the game loop, managing turns and checking for game-end conditions.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        Player currentPlayer = player1; // Start with the first player
        boolean gameEnded = false; // Track whether the game is over

        // Game loop
        while (!gameEnded) {
            board.display(); // Show the current state of the board
            System.out.println("Player " + currentPlayer.getSymbol() + "'s turn.");
            currentPlayer.getMove(board); // Get the player's move

            // Check if the current player has won
            if (checkWinner(currentPlayer.getSymbol())) {
                board.display();
                System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                System.out.print("Do you want to play again? (yes/no): ");
                String playAgain = scanner.next();
                if (playAgain.equalsIgnoreCase("yes")) {
                    board.reset(); // Reset the board for a new game
                    currentPlayer = player1; // Restart with player 1
                    continue;
                } else {
                    System.out.println("Thanks for playing!");
                    gameEnded = true;
                }
            } 
            // Check if the board is full (resulting in a draw)
            else if (board.isFull()) {
                board.display();
                System.out.println("It's a draw!");
                System.out.print("Do you want to play again? (yes/no): ");
                String playAgain = scanner.next();
                if (playAgain.equalsIgnoreCase("yes")) {
                    board.reset(); // Reset the board for a new game
                    currentPlayer = player1; // Restart with player 1
                    continue;
                } else {
                    System.out.println("Thanks for playing!");
                    gameEnded = true;
                }
            } else {
                // Switch to the next player
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        }
    }

    //Main method to initialize and start the game.
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the upgraded Tic-Tac-Toe!");

        // Get grid size (N)
        int N;
        while (true) {
            try {
                System.out.print("Enter the size of the grid (3 to 20): ");
                N = scanner.nextInt();
                if (N >= 3 && N <= 20) {
                    break;
                } else {
                    System.out.println("Invalid grid size. Please choose a size between 3 and 20.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 3 and 20.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Winning condition (M consecutive symbols, default to N)
        int M = N;
        System.out.println("The winning condition set to " + M + " consecutive symbols.");

        // Choose game mode
        int choice = -1;
        while (true) {
            try {
                System.out.println("Choose your game mode:");
                System.out.println("1. Human vs. Human");
                System.out.println("2. Human vs. Computer");
                System.out.println("3. Computer vs. Computer");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid option. Please choose a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.next(); // Clear the invalid input
            }
        }

        Player player1, player2;

        // Initialize players based on user choice
        switch (choice) {
            case 1 -> {
                player1 = new HumanPlayer('X');
                player2 = new HumanPlayer('O');
            }
            case 2 -> {
                player1 = new HumanPlayer('X');
                player2 = new ComputerPlayer('O');
            }
            case 3 -> {
                player1 = new ComputerPlayer('X');
                player2 = new ComputerPlayer('O');
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }

        // Start the game
        Upgrade game = new Upgrade(player1, player2, N, M);
        game.start();
    }
}
