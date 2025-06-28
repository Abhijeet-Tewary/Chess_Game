public class Queen extends Piece {
    public Queen(String color) {
        super(color, "queen");
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        // Diagonal move (like Bishop)
        if (rowDiff == colDiff) {
            int rowStep = (toRow > fromRow) ? 1 : -1;
            int colStep = (toCol > fromCol) ? 1 : -1;

            int r = fromRow + rowStep;
            int c = fromCol + colStep;

            while (r != toRow && c != toCol) {
                if (board[r][c] != null) return false;
                r += rowStep;
                c += colStep;
            }

            return board[toRow][toCol] == null ||
                   !board[toRow][toCol].getColor().equals(this.color);
        }

        // Straight move (like Rook)
        if (fromRow == toRow || fromCol == toCol) {
            if (fromRow == toRow) {
                int step = (toCol > fromCol) ? 1 : -1;
                for (int col = fromCol + step; col != toCol; col += step) {
                    if (board[fromRow][col] != null) return false;
                }
            } else {
                int step = (toRow > fromRow) ? 1 : -1;
                for (int row = fromRow + step; row != toRow; row += step) {
                    if (board[row][fromCol] != null) return false;
                }
            }

            return board[toRow][toCol] == null ||
                   !board[toRow][toCol].getColor().equals(this.color);
        }

        // ❗ For any non-diagonal, non-straight move
        return false;
    }
}
