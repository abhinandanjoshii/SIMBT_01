import java.util.Scanner;

public class InternSimplByte {
    private char[][] board;
    private char currentPlayer;

    public InternSimplByte() {
        board = new char[3][3];
        currentPlayer = 'X';


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }

    public void handleMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("Enter the row number (0-2): ");
            row = scanner.nextInt();
            System.out.print("Enter the column number (0-2): ");
            col = scanner.nextInt();
        } while (!isValidMove(row, col));

        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            System.out.println("Invalid move! Row and column should be between 0 and 2.");
            return false;
        }
        if (board[row][col] != ' ') {
            System.out.println("Invalid move! The position is already occupied.");
            return false;
        }
        return true;
    }

    public boolean checkForWinner() {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return true;
            }
        }


        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return true;
            }
        }


        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        InternSimplByte game = new InternSimplByte();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tic Tac Toe Game");

        while (true) {
            System.out.println("Current board:");
            game.printBoard();

            System.out.println("Player " + game.currentPlayer + "'s turn:");
            game.handleMove();

            if (game.checkForWinner()) {
                System.out.println("Player " + game.currentPlayer + " wins!");
                break;
            } else if (game.isBoardFull()) {
                System.out.println("It's a tie!");
                break;
            }
        }

        scanner.close();
    }
}
