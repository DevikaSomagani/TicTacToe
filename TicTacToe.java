import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];

        // Initialize the board with dashes (empty positions)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        // Ask the players for their names
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's play Tic Tac Toe!");
        System.out.print("Player 1, what is your name? ");
        String p1 = scanner.nextLine();
        System.out.print("Player 2, what is your name? ");
        String p2 = scanner.nextLine();

        // Create a boolean to track the current player
        boolean player1 = true;

        // Create a boolean to track if the game has ended
        boolean gameEnded = false;
        while (!gameEnded) {
            // Draw the board
            drawBoard(board);

            // Print whose turn it is
            if (player1) {
                System.out.println(p1 + "'s Turn (x):");
            } else {
                System.out.println(p2 + "'s Turn (o):");
            }

            // Determine the character for the current player
            char c = player1 ? 'x' : 'o';

            // Get the row and column input from the user
            int row, col;
            while (true) {
                System.out.print("Enter a row number (0, 1, or 2): ");
                row = scanner.nextInt();
                System.out.print("Enter a column number (0, 1, or 2): ");
                col = scanner.nextInt();

                // Check if the input is valid
                if (row < 0 || col < 0 || row > 2 || col > 2) {
                    System.out.println("This position is off the bounds of the board! Try again.");
                } else if (board[row][col] != ' ') {
                    System.out.println("Someone has already made a move at this position! Try again.");
                } else {
                    break;
                }
            }

            // Set the position on the board
            board[row][col] = c;

            // Check if the game has ended
            char winner = playerHasWon(board);
            if (winner == 'x') {
                System.out.println(p1 + " has won!");
                gameEnded = true;
            } else if (winner == 'o') {
                System.out.println(p2 + " has won!");
                gameEnded = true;
            } else {
                // If neither player has won, check for a tie
                if (boardIsFull(board)) {
                    System.out.println("It's a tie!");
                    gameEnded = true;
                } else {
                    // Switch players
                    player1 = !player1;
                }
            }
        }

        // Draw the final board
        drawBoard(board);
    }
    // Function to draw the tic tac toe board
    public static void drawBoard(char[][] board) {
        System.out.println("Board:");
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Function to check if a player has won
    public static char playerHasWon(char[][] board) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return board[i][0];
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '-') {
            return board[2][0];
        }

        // No winner yet
        return ' ';
    }

    // Function to check if the board is full
    public static boolean boardIsFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
