import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chessboard {
    static Piece[][] gameBoard = new Piece[8][8];
    static JLabel[][] labels = new JLabel[8][8];
    static JPanel[][] squares = new JPanel[8][8];
    static int selectedRow = -1;
    static int selectedCol = -1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel panel = new JPanel(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBackground((i + j) % 2 == 0 ? Color.BLACK : Color.WHITE);

                JLabel label = new JLabel();
                labels[i][j] = label;
                squares[i][j] = square;

                int row = i, col = j;
                square.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        handleClick(row, col);
                    }
                });

                square.add(label);
                panel.add(square);
            }
        }

        // Setup all pieces
        setupPieces();

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void setupPieces() {
        // Setup pawns
        for (int col = 0; col < 8; col++) {
            gameBoard[6][col] = new Pawn("white");
            setPieceIcon(6, col, "white_pawn");

            gameBoard[1][col] = new Pawn("black");
            setPieceIcon(1, col, "black_pawn");
        }
        gameBoard[7][2] = new Knight("white");
            setPieceIcon(7, 2, "white_knight");
        gameBoard[0][2] = new Knight("black");
            setPieceIcon(0, 2, "black_knight");
        gameBoard[7][5] = new Knight("white");
            setPieceIcon(7, 5, "white_knight");
        gameBoard[0][5] = new Knight("black");
            setPieceIcon(0, 5, "black_knight");
        gameBoard[7][1] = new Bishop("white");
            setPieceIcon(7, 1, "white_bishop");
        gameBoard[0][1] = new Bishop("black");
            setPieceIcon(0, 1, "black_bishop");
        gameBoard[7][6] = new Bishop("white");
            setPieceIcon(7, 6, "white_bishop");
        gameBoard[0][6] = new Bishop("black");
            setPieceIcon(0, 6, "black_bishop");
        gameBoard[7][3] = new Queen("white");
            setPieceIcon(7, 3, "white_queen");
        gameBoard[0][3] = new Queen("black");
            setPieceIcon(0, 3, "black_queen");
        gameBoard[7][4] = new King("white");
            setPieceIcon(7, 4, "white_king");
        gameBoard[0][4] = new King("black");
            setPieceIcon(0, 4, "black_king");
        gameBoard[7][0] = new Rook("white");
            setPieceIcon(7, 0, "white_rook");   
        gameBoard[0][0] = new Rook("black");
            setPieceIcon(0, 0, "black_rook");
        gameBoard[7][7] = new Rook("white");
            setPieceIcon(7, 7, "white_rook");
        gameBoard[0][7] = new Rook("black");
            setPieceIcon(0, 7, "black_rook");

        
    }

    public static void handleClick(int row, int col) {
        if (selectedRow == -1 && gameBoard[row][col] != null) {
            selectedRow = row;
            selectedCol = col;
            squares[row][col].setBackground(Color.YELLOW);
        } else if (selectedRow != -1) {
            Piece selectedPiece = gameBoard[selectedRow][selectedCol];
                    
            if (selectedPiece != null &&
                selectedPiece.isValidMove(selectedRow, selectedCol, row, col, gameBoard)) {

                gameBoard[row][col] = selectedPiece;
                gameBoard[selectedRow][selectedCol] = null;

                labels[row][col].setIcon(labels[selectedRow][selectedCol].getIcon());
                labels[selectedRow][selectedCol].setIcon(null);
            }

            resetBoardColors();
            selectedRow = -1;
            selectedCol = -1;
        }
    }

    public static void setPieceIcon(int row, int col, String pieceName) {
        ImageIcon icon = new ImageIcon("C:/Users/abhij/OneDrive/Desktop/image/" + pieceName + ".png");
        Image image = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        labels[row][col].setIcon(new ImageIcon(image));
    }

    public static void resetBoardColors() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setBackground((i + j) % 2 == 0 ? Color.BLACK : Color.WHITE);
            }
        }
    }
}
