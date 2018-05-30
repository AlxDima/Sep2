public class MoveR {

    private MoveT type;
    private Piece piece;

    public MoveT getType() {
        return type;
    }

    public Piece getPiece() {
        return piece;
    }

    public MoveR(MoveT type) {
        this(type, null);
    }

    public MoveR(MoveT type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }
    
    private MoveR tryMove(Piece piece, int newX, int newY) {
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveR(MoveT.NONE);
        }

        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            return new MoveR(MoveT.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

        if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                return new MoveR(MoveT.KILL, board[x1][y1].getPiece());
            }
        }

        return new MoveR(MoveT.NONE);
    }
}