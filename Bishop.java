public class Bishop extends Piece {
    public Bishop(String color) {
        super(color, "bishop");
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        // Bishops must move diagonally
        if (rowDiff != colDiff) return false;

        int rowStep = (toRow > fromRow) ? 1 : -1;
        int colStep = (toCol > fromCol) ? 1 : -1;

        int r = fromRow + rowStep;
        int c = fromCol + colStep;

        // Check all intermediate squares for obstruction
        while (r != toRow && c != toCol) {
            if (board[r][c] != null) return false;
            r += rowStep;
            c += colStep;
        }

        // Destination must be empty or contain opponent piece
        return board[toRow][toCol] == null ||
               !board[toRow][toCol].getColor().equals(this.color);
    }
}

