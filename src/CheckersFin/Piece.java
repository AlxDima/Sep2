package CheckersFin;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class Piece extends StackPane {


	
	private PieceType type;
	private double mouseX, mouseY;
    private double oldX, oldY;

    public PieceType getType() {
        return type;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Piece(PieceType type, int x, int y) {
        this.type = type;

        move(x, y);

        Ellipse bg = new Ellipse(Board.TILE_SIZE * 0.3125, Board.TILE_SIZE * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(Board.TILE_SIZE * 0.03);

        bg.setTranslateX((Board.TILE_SIZE - Board.TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((Board.TILE_SIZE - Board.TILE_SIZE * 0.26 * 2) / 2 + Board.TILE_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(Board.TILE_SIZE * 0.3125, Board.TILE_SIZE * 0.26);
        ellipse.setFill(type == PieceType.RED
                ? Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(Board.TILE_SIZE * 0.03);

        ellipse.setTranslateX((Board.TILE_SIZE - Board.TILE_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((Board.TILE_SIZE - Board.TILE_SIZE * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });
    }

    public void move(int x, int y) {
        oldX = x * Board.TILE_SIZE;
        oldY = y * Board.TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }
    
    

}