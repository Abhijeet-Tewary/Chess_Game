public class Rook extends Piece {
    public Rook(String color) {
        super(color, "rook");
    }
    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        // Rook moves either in the same row or same column
        if (fromRow != toRow && fromCol != toCol) {
            return false; // Not a straight line
        }

        // Moving along the column
        if (fromCol == toCol) {
            int step = (toRow > fromRow) ? 1 : -1;
            for (int row = fromRow + step; row != toRow; row += step) {
                if (board[row][fromCol] != null) {
                    return false; // Something is in the way
                }
            }
        }

        // Moving along the row
        if (fromRow == toRow) {
            int step = (toCol > fromCol) ? 1 : -1;
            for (int col = fromCol + step; col != toCol; col += step) {
                if (board[fromRow][col] != null) {
                    return false; // Something is in the way
                }
            }
        }

        // Destination must be empty or opponent's piece
        return board[toRow][toCol] == null ||
               !board[toRow][toCol].getColor().equals(this.color);
    }
}

