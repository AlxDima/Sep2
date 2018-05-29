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
}