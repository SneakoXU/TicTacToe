import java.util.ArrayList;
import java.util.Objects;

public class TicTacToe {
    public enum Turn {
        X ,
        O
    }
    private static final int DEFAULT_BOARD_SIZE = 3;
    private int boardSize;
    private TicTacToeTile [][] board;
    private Boolean isOver;

    private Turn currentTurn;
    private String result;

    public TicTacToeTile[][] getBoard() {
        return board;
    }

    public TicTacToe() {
        this(DEFAULT_BOARD_SIZE);
    }

    public TicTacToe(int boardSize) {
        // initialize the board
        this.boardSize = boardSize;
        this.board = new TicTacToeTile[boardSize][boardSize];
        currentTurn = Turn.X;
        isOver = false;
        result = null;

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                this.board[row][col] = new TicTacToeTile(row, col);
            }
        }
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }

    public Boolean isOver() {
        return isOver;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void playTurn(int row, int col) {
        this.board[row][col].setValue(currentTurn.name());
        toggleTurn();
    }

    private void toggleTurn(){
        currentTurn = currentTurn == Turn.X ? Turn.O : Turn.X;
    }

    public void calculateResult() {
        ArrayList<String> results = new ArrayList<>();
        results.add(checkDiagonalWin());
        results.add(checkVerticalWin());
        results.add(checkHorizontalWin());

        result = results.stream().filter(Objects::nonNull).findFirst().orElse(null);
        if(result == null) result = isTie() ? "tie": null;

        if(result != null){
            isOver = true;
        }
    }

    private boolean isTie() {
        // if non of the tiles are empty and no winner, then it's a tie
        for (int row = 0; row < boardSize; ++row) {
            for (int col = 0; col < boardSize; ++col) {
                if (" ".equals(board[row][col].getValue())) {
                    return false;  // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // no empty cell, it's a draw
    }

    private String checkDiagonalWin() {
        int XcountDia = 0;
        int OcountDia = 0;
        int XcountRevDia = 0;
        int OcountRevDia = 0;

        for (int element = 0; element < boardSize; element++) {
            if (("X").equalsIgnoreCase(board[element][element].getValue())) {
                XcountDia++;
            }
            if (("O").equalsIgnoreCase(board[element][element].getValue())) {
                OcountDia++;
            }
            if (("X").equalsIgnoreCase(board[boardSize - element - 1][element].getValue())) {
                XcountRevDia++;
            }
            if (("O").equalsIgnoreCase(board[boardSize - element - 1][element].getValue())) {
                OcountRevDia++;
            }
        }

        if (XcountDia == boardSize || XcountRevDia == boardSize) {
            return "X";
        } else if (OcountDia == boardSize || OcountRevDia == boardSize) {
            return "O";
        } else {
            return null;
        }
    }

    private String checkHorizontalWin() {
        int Xcount = 0;
        int Ocount = 0;
        for (int row = 0; row < boardSize; row++) {
            Xcount = 0;
            Ocount = 0;
            for (int col = 0; col < boardSize; col++) {
                if (("X").equalsIgnoreCase(board[row][col].getValue())) {
                    Xcount++;
                } else if (("O").equalsIgnoreCase(board[row][col].getValue())) {
                    Ocount++;
                }
            }
//			System.out.println("O " + row + " " + Ocount);
//			System.out.println("X "+ row + " " + Xcount);
            if (Xcount == boardSize || Ocount == boardSize) {
                break;
            }
        }
        if (Xcount == boardSize) {
            return "X";
        } else if (Ocount == boardSize) {
            return "O";
        } else {
            return null;
        }
    }

    private String checkVerticalWin() {
        int Xcount = 0;
        int Ocount = 0;
        for (int col = 0; col < boardSize; col++) {
            Xcount = 0;
            Ocount = 0;
            for (int row = 0; row < boardSize; row++) {
                if (("X").equalsIgnoreCase(board[row][col].getValue())) {
                    Xcount++;
                } else if (("O").equalsIgnoreCase(board[row][col].getValue())) {
                    Ocount++;
                }
            }
            if (Xcount == boardSize || Ocount == boardSize) {
                break;
            }
        }
        if (Xcount == boardSize) {
            return "X";
        } else if (Ocount == boardSize) {
            return "O";
        } else {
            return null;
        }
    }

    public String getResult() {
        return result;
    }

    public String toString() {
        String r = "";
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++){
                r = r + " " + board[row][col].getValue() + " ";
                if(col != boardSize - 1) {
                    r = r + "|";
                }
            }
            if(row != boardSize - 1) {
                r = r + "\n-----------\n";

            }
        }
        return r;
    }

}
