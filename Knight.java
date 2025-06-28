public class Knight extends Piece {
    public Knight(String color) {
        super(color, "knight");
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        // Knight moves in an L-shape: (2,1) or (1,2)
        boolean isLShape = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        if (!isLShape) return false;

        // Can't capture own piece
        if (board[toRow][toCol] != null && board[toRow][toCol].getColor().equals(this.color)) {
            return false;
        }

        return true;
    }
}

