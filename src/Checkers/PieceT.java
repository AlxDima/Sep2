package Checkers;

public enum PieceT {
    RED(1), WHITE(-1);

    final int moveDir;

    PieceT(int moveDir) {
        this.moveDir = moveDir;
    }
}