package Checkers;

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

        Ellipse bg = new Ellipse(CheckersMain.TILE_SIZE * 0.3125, CheckersMain.TILE_SIZE * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(CheckersMain.TILE_SIZE * 0.03);

        bg.setTranslateX((CheckersMain.TILE_SIZE - CheckersMain.TILE_SIZE * 0.3125 * 2) / 2);
        bg.setTranslateY((CheckersMain.TILE_SIZE - CheckersMain.TILE_SIZE * 0.26 * 2) / 2 + CheckersMain.TILE_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(CheckersMain.TILE_SIZE * 0.3125, CheckersMain.TILE_SIZE * 0.26);
        ellipse.setFill(type == PieceType.RED
                ? Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(CheckersMain.TILE_SIZE * 0.03);

        ellipse.setTranslateX((CheckersMain.TILE_SIZE - CheckersMain.TILE_SIZE * 0.3125 * 2) / 2);
        ellipse.setTranslateY((CheckersMain.TILE_SIZE - CheckersMain.TILE_SIZE * 0.26 * 2) / 2);

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
        oldX = x * CheckersMain.TILE_SIZE;
        oldY = y * CheckersMain.TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }
}