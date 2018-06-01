package CheckersFin;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class Board {

public static final int TILE_SIZE = 50;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    static Tile[][] board = new Tile[WIDTH][HEIGHT];

    private static Group tileGroup = new Group();
    private static Group pieceGroup = new Group();
    private static boolean whiteTurn = true;
   

    
    
    
   

    public static Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }
    
    
    
    public static MoveR tryMove(Piece piece, int newX, int newY) {
    	
    	
    	if (piece.getType() == PieceType.WHITE && whiteTurn)
    	{
    		whiteTurn = false;	
    	
    	
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveR(MoveT.NONE);
        }
        
        int x0 = Board.toBoard(piece.getOldX());
        int y0 = Board.toBoard(piece.getOldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
            return new MoveR(MoveT.NORMAL);
        } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

        if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                return new MoveR(MoveT.KILL, board[x1][y1].getPiece());
            }
        }
        
    	} else if (piece.getType() == PieceType.RED && !whiteTurn){
    		
    		whiteTurn = true;
            if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
                return new MoveR(MoveT.NONE);
            }

            int x0 = Board.toBoard(piece.getOldX());
            int y0 = Board.toBoard(piece.getOldY());

            if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
                return new MoveR(MoveT.NORMAL);
            } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                    return new MoveR(MoveT.KILL, board[x1][y1].getPiece());
                }
            }
            
    	}
    	
    	
    	
        return new MoveR(MoveT.NONE);
    
		
    }
    
    public static Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = Board.toBoard(piece.getLayoutX());
            int newY = Board.toBoard(piece.getLayoutY());

            MoveR result;

            if (newX < 0 || newY < 0 || newX >= Board.WIDTH || newY >= Board.HEIGHT) {
                result = new MoveR(MoveT.NONE);
            } else {
                result = tryMove(piece, newX, newY);
                tryMove(piece, newX, newY);
            }

            int x0 = Board.toBoard(piece.getOldX());
            int y0 = Board.toBoard(piece.getOldY());

            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[Board.toBoard(otherPiece.getOldX())][Board.toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;	
            }
        });

        return piece;
    }
	



	public static int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
	}
}
