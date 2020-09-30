import java.util.Scanner;

public class TicTacToeConsoleRunner {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean done = false;

        TicTacToe game = new TicTacToe();

        while (!done)
        {
            System.out.print(game.toString());
            int row = SafeInput.getRangedInt(in, "Row for " + game.getCurrentTurn().name() + " (-1 to exit): ", -1, 2);
            if (row < 0) System.exit(0);
            int col = SafeInput.getRangedInt(in, "Column for " + game.getCurrentTurn().name() + " (-1 to exit): ", -1, 2);
            if (col < 0) System.exit(0);
            game.playTurn(row, col);
            game.calculateResult();

            if(game.isOver()) {
                System.out.println("The game is over and the Result is: " + game.getResult());
                done = SafeInput.getYNConfirm(in,"Play Again?");
            }

        }
    }
}
