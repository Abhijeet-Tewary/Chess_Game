public class King extends Piece {
    private boolean hasMoved = false;

    public King(String color) {
        super(color, "king");
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        // Normal 1-square move
        if (rowDiff <= 1 && colDiff <= 1 && (rowDiff + colDiff != 0)) {
            if (board[toRow][toCol] == null ||
                !board[toRow][toCol].getColor().equals(this.color)) {
                return true;
            }
        }

        // ✅ Castling
        if (!hasMoved && fromRow == toRow) {
            // King-side castling (short)
            if (toCol == fromCol + 2) {
                Piece rook = board[fromRow][7];
                if (rook instanceof Rook && !((Rook) rook).hasMoved()) {
                    if (board[fromRow][5] == null && board[fromRow][6] == null) {
                        return true;
                    }
                }
            }

            // Queen-side castling (long)
            if (toCol == fromCol - 2) {
                Piece rook = board[fromRow][0];
                if (rook instanceof Rook && !((Rook) rook).hasMoved()) {
                    if (board[fromRow][1] == null && board[fromRow][2] == null && board[fromRow][3] == null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
