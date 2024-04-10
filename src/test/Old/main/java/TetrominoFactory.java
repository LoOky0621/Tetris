package src.main.java;

import java.awt.Color;
import java.util.Random;

public class TetrominoFactory {
    // Eine Liste von vordefinierten Tetrominos in Form von 2D-Arrays
    private static final int[][][] TETROMINOS = {
        Block.L_BLOCK,
        Block.I_BLOCK,
        Block.O_BLOCK,
        Block.Z_BLOCK,
        Block.RL_BLOCK,
        Block.RZ_BLOCK
    };

    // Eine Liste von Farben, die den Tetrominos zugeordnet sind
    private static final Color[] COLORS = {
        Block.L_COLOR,
        Block.I_COLOR,
        Block.O_COLOR,
        Block.Z_COLOR,
        Block.RL_COLOR,
        Block.RZ_COLOR
    };

    // Eine Instanz der Random-Klasse zur Generierung zufälliger Tetrominos
    private static final Random random = new Random();

    // Eine Methode, um ein zufälliges Tetromino auszuwählen und zurückzugeben
    public static Tetromino getRandomTetromino() {
        // Generiere einen zufälligen Index, um ein Tetromino auszuwählen
        int randomIndex = random.nextInt(TETROMINOS.length);

        // Holen Sie sich das ausgewählte Tetromino-Form-Array und die dazugehörige Farbe
        int[][] shape = TETROMINOS[randomIndex];
        Color color = COLORS[randomIndex];

        // Erstellen Sie ein Tetromino-Objekt mit der ausgewählten Form und Farbe
        return new Tetromino(shape, color);
    }
}
