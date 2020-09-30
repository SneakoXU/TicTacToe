import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {

    JPanel main, topPanel, gameBoardPanel, buttonPanel, resultsPanel;
    JButton quit;
    ImageIcon quitIcon;
    JLabel mainLabel, XWinsLabel, OWinsLabel, tiesLabel;

    Font gameBoardFont, XOButtons, mainLabelFont;

    TicTacToe game = new TicTacToe();
    TicTacToeTile[][] board = game.getBoard();
    int Owins = 0;
    int Xwins = 0;
    int ties = 0;


    public TicTacToeFrame() {
        super("Tic Tac Toe");
        setSize(700,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        UISetup();
    }


    private void UISetup() {

        //top panel with mainLabel
        topPanel = new JPanel();
        mainLabel = new JLabel("Tic Tac Toe");
        mainLabelFont = new Font("Helvetica", Font.BOLD, 48);
        mainLabel.setFont(mainLabelFont);
        topPanel.add(mainLabel);

        //gameBoardPanel with board
        gameBoardPanel = new JPanel();
        for (int row=0; row<game.getBoardSize(); row++){
            for (int col=0; col<game.getBoardSize(); col++){
                gameBoardPanel.add(board[row][col]);
                XOButtons =  new Font("Helvetica", Font.BOLD, 30);
                board[row][col].setFont(XOButtons);
                board[row][col].addActionListener(actionEvent -> {
                    TicTacToeTile selected = (TicTacToeTile) actionEvent.getSource();
                    game.playTurn(selected.getRow(), selected.getColumn());
                    selected.setForeground(
                            game.getCurrentTurn().name().equals("X") ? Color.BLUE: Color.RED
                    );

                    game.calculateResult();

                    if(game.isOver()) {
                        System.out.println("The game is over and the Result is: " + game.getResult());
                        updateGameResults();
                        Boolean done = SafeInput.getYNConfirmDialog("Play Again?");
                        if(!done) {
                            System.exit(0);
                        }
                        resetGame();
                    }
                });
            }
        }

        //buttonPanel with quit button
        buttonPanel = new JPanel();
        quitIcon = new ImageIcon("assets/quit.png");
        quit = new JButton("Quit", quitIcon);
        quit.addActionListener(actionEvent ->
                {
                    System.exit(0);
                }
        );
        buttonPanel.add(quit);

        //resultsPanel with stat labels
        resultsPanel = new JPanel();
        gameBoardFont = new Font("Helvetica", Font.BOLD, 20);
        XWinsLabel = new JLabel("X: " + Xwins);
        XWinsLabel.setFont(gameBoardFont);
        OWinsLabel = new JLabel("O: " + Owins);
        OWinsLabel.setFont(gameBoardFont);
        tiesLabel = new JLabel("Ties: " + ties);
        tiesLabel.setFont(gameBoardFont);
        resultsPanel.add(XWinsLabel);
        resultsPanel.add(OWinsLabel);
        resultsPanel.add(tiesLabel);

        //add sub panels to main panel
        main = new JPanel();
        main.setLayout(new BorderLayout());
        main.add(topPanel, BorderLayout.NORTH);
        main.add(gameBoardPanel, BorderLayout.CENTER);
        gameBoardPanel.setLayout(new GridLayout(3,3));
        main.add(resultsPanel, BorderLayout.EAST);
        resultsPanel.setLayout(new GridLayout(3,1));
        main.add(buttonPanel, BorderLayout.SOUTH);

        //add main to JFrame
        add(main);
    }

    private void resetGame() {
        for (int row=0; row<game.getBoardSize(); row++) {
            for (int col = 0; col < game.getBoardSize(); col++) {
                board[row][col].setValue(" ");
            }
        }}

    private void updateGameResults(){
        if(game.getResult().equals("X")){
            Xwins++;
            XWinsLabel.setText("X wins: " + Xwins);
        }
        else if (game.getResult().equals("O")){
            Owins++;
            OWinsLabel.setText("O wins: " + Owins);
        }
        else{
            ties++;
            tiesLabel.setText("Ties: " + ties);
        }

    }
}
