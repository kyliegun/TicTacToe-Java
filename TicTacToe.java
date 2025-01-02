/**
 * Kylie Gun
 * 400524717
 * McMaster University 
 * December 4, 2024
 * 2OP3 - Object Oriented Programming Fall 2024
 */

 import java.util.Random;
 import java.util.Scanner;
 
 public class TicTacToe {
 
     // The Board class represents the Tic Tac Toe game board
     public static class Board {
         private char[][] grid;  // 3x3 grid to store game state
 
         // Constructor to initialize the grid
         public Board() {
             grid = new char[3][3];  // Initialize a 3x3 grid
             reset();  // Reset the grid to an empty state
         }
 
         // Resets the grid to its initial empty state
         public void reset() {
             for (int i = 0; i < grid.length; i++) {
                 for (int j = 0; j < grid[i].length; j++) {
                     grid[i][j] = ' ';  // Set each cell to empty space
                 }
             }
         }
 
         // Displays the current state of the board
         public void display() {
             System.out.println();
             for (int i = 0; i < grid.length; i++) {
                 for (int j = 0; j < grid[i].length; j++) {
                     System.out.print(grid[i][j]);
                     if (j < grid[i].length - 1) System.out.print(" | ");  // Print separator between cells
                 }
                 System.out.println();
                 if (i < grid.length - 1) System.out.println("-".repeat(grid[i].length * 4 - 3));  // Print separator between rows
             }
             System.out.println();
         }
 
         // Places a marker ('X' or 'O') at the given row and column if the cell is empty
         public boolean placeMarker(int row, int col, char symbol) {
             if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length && grid[row][col] == ' ') {
                 grid[row][col] = symbol;  // Place the symbol in the grid
                 return true;  // Move successful
             }
             return false;  // Move failed (cell already occupied or out of bounds)
         }
 
         // Checks if a player has won by checking rows, columns, and diagonals
         public boolean checkWinner(char symbol) {
             for (int i = 0; i < grid.length; i++) {
                 // Check each row and column for a win
                 if ((grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) ||
                     (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol)) {
                     return true;
                 }
             }
             // Check both diagonals for a win
             return (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
                    (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol);
         }
 
         // Checks if the board is full (no empty spaces)
         public boolean isFull() {
             for (char[] row : grid) {
                 for (char cell : row) {
                     if (cell == ' ') return false;  // Return false if any cell is empty
                 }
             }
             return true;  // Return true if no empty cells are found
         }
     }
 
     // Player interface to define player behavior
     public interface Player {
         char getSymbol();  // Get the symbol for the player ('X' or 'O')
         int[] getMove(Board board);  // Get the next move (row and column)
     }
 
     // HumanPlayer class represents a player controlled by a human
     public static class HumanPlayer implements Player {
         private final char symbol;  // Symbol for the human player
         private final Scanner scanner;  // Scanner to take input from user
 
         // Constructor to initialize the symbol and scanner
         public HumanPlayer(char symbol) {
             this.symbol = symbol;
             this.scanner = new Scanner(System.in);  // Initialize scanner for input
         }
 
         // Returns the symbol of the player
         @Override
         public char getSymbol() {
             return symbol;
         }
 
         // Get the player's move (row and column)
         @Override
         public int[] getMove(Board board) {
             int row, col;
             while (true) {
                 try {
                     System.out.print("Enter your move (row and column: 1-3): ");
                     String input = scanner.nextLine();  // Read user input
                     String[] parts = input.trim().split("\\s+");  // Split input into row and column
 
                     if (parts.length != 2) {
                         throw new IllegalArgumentException("Please enter exactly two numbers separated by a space.");
                     }
 
                     row = Integer.parseInt(parts[0]);  // Parse row number
                     col = Integer.parseInt(parts[1]);  // Parse column number
 
                     if (board.placeMarker(row - 1, col - 1, symbol)) {
                         break;  // Exit loop if move is valid
                     } else {
                         System.out.println("Invalid move. The cell is already occupied or out of bounds.");
                     }
                 } catch (NumberFormatException e) {
                     System.out.println("Invalid input. Please enter numeric values only.");
                 } catch (IllegalArgumentException e) {
                     System.out.println(e.getMessage());
                 }
             }
             return new int[]{row, col};  // Return the chosen row and column
         }
     }
 
     // ComputerPlayer class represents a player controlled by the computer
     public static class ComputerPlayer implements Player {
         private final char symbol;  // Symbol for the computer player
         private final Random random;  // Random number generator for selecting a move
 
         // Constructor to initialize the symbol and random number generator
         public ComputerPlayer(char symbol) {
             this.symbol = symbol;
             this.random = new Random();
         }
 
         // Returns the symbol of the computer player
         @Override
         public char getSymbol() {
             return symbol;
         }
 
         // Get the computer's move (random move selection)
         @Override
         public int[] getMove(Board board) {
             int row, col;
             while (true) {
                 row = random.nextInt(3);  // Randomly select a row
                 col = random.nextInt(3);  // Randomly select a column
                 if (board.placeMarker(row, col, symbol)) {
                     System.out.println("Computer placed " + symbol + " at " + (row + 1) + " " + (col + 1));
                     break;  // Exit loop when a valid move is found
                 }
             }
             return new int[]{row, col};  // Return the chosen row and column
         }
     }
 
     // Main method to run the game
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
 
         System.out.println("Welcome to Tic Tac Toe!");
         System.out.println("Choose your game mode:");
         System.out.println("1. Human vs. Human");
         System.out.println("2. Human vs. Computer");
         System.out.println("3. Computer vs. Computer");
 
         int choice = -1;
         while (true) {
             try {
                 choice = scanner.nextInt();  // Get user input for game mode
                 if (choice >= 1 && choice <= 3) {
                     break;  // Exit loop if valid choice is made
                 } else {
                     System.out.println("Invalid option. Please choose a number between 1 and 3.");
                 }
             } catch (Exception e) {
                 System.out.println("Invalid input. Please enter a number between 1 and 3.");
                 scanner.next();  // Consume invalid input
             }
         }
 
         Player player1;
         Player player2;
 
         // Create players based on the chosen game mode
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
 
         Board board = new Board();  // Create a new board
         Player currentPlayer = player1;  // Set player1 as the starting player
 
         board.display();  // Display the empty board
 
         while (true) {
             System.out.println("Player " + currentPlayer.getSymbol() + "'s turn.");
             currentPlayer.getMove(board);  // Get the current player's move
             board.display();  // Display the updated board
 
             if (board.checkWinner(currentPlayer.getSymbol())) {
                 System.out.println("Player " + currentPlayer.getSymbol() + " wins!");  // Announce winner
                 break;
             }
 
             if (board.isFull()) {
                 System.out.println("It's a draw!");  // Announce draw if the board is full
                 break;
             }
 
             currentPlayer = (currentPlayer == player1) ? player2 : player1;  // Switch to the next player
         }
 
         System.out.println("Game over. Thanks for playing!");
     }
 
     // These methods seem unnecessary but could be kept for future functionality
     public Board getBoard() {
         return board;
     }
 
     public Player getPlayer1() {
         return player1;
     }
 
     public Player getPlayer2() {
         return player2;
     }
 }
 
