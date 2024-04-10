package src.main.java;

import java.awt.Color;

public class Block {
    // Farben für Tetrominos
    public static final Color L_COLOR = Color.ORANGE;
    public static final Color I_COLOR = Color.GREEN;
    public static final Color O_COLOR = Color.GRAY;
    public static final Color Z_COLOR = Color.RED;
    public static final Color RL_COLOR = Color.BLUE;
    public static final Color RZ_COLOR = Color.BLACK;

    // Formen für Tetrominos
    public static final int[][] L_BLOCK = {
        {1, 0},
        {1, 0},
        {1, 1}
    };

    public static final int[][] I_BLOCK = {
        {1, 0},
        {1, 0},
        {1, 0},
        {1, 0}
    };

    public static final int[][] O_BLOCK = {
        {1, 1},
        {1, 1}
    };

    public static final int[][] Z_BLOCK = {
        {1, 0},
        {1, 1},
        {0, 1}
    };

    public static final int[][] RL_BLOCK = {
        {0, 1},
        {0, 1},
        {1, 1}
    };

    public static final int[][] RZ_BLOCK = {
        {0, 1},
        {1, 1},
        {1, 0}
    };

    private Color color;
    private boolean occupied; 

    // Konstruktor: Initialisiert einen Block mit einer Farbe und als besetzt.
    public Block(Color color) {
        this.color = color;
        this.occupied = true;
    }

    // Gibt die Farbe des Blocks zurück.
    public Color getColor() {
        return color;
    }

    // Prüft, ob der Block besetzt ist.
    public boolean isOccupied() {
        return occupied;
    }

    // Legt fest, ob der Block besetzt oder nicht besetzt ist.
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
