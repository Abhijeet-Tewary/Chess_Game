public class Pawn extends Piece {
    public Pawn(String color) {
        super(color, "pawn");
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        int direction = color.equals("white") ? -1 : 1;

        if (fromCol == toCol && board[toRow][toCol] == null) {
            if (toRow == fromRow + direction) {
                return true;
            }

            if (((color.equals("white") && fromRow == 6) || (color.equals("black") && fromRow == 1))
                    && toRow == fromRow + 2 * direction
                    && board[fromRow + direction][fromCol] == null
                    && board[toRow][fromCol] == null) {
                return true;
            }
        }

        if (Math.abs(toCol - fromCol) == 1 && toRow == fromRow + direction) {
            return board[toRow][toCol] != null && !board[toRow][toCol].getColor().equals(color);
        }

        return false;
    }
}
