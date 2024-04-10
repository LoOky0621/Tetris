package src.main.java;

import java.awt.Color;

public class Tetromino {
    private int[][] shape; // Die Form des Tetrominos
    private Color color;   // Die Farbe des Tetrominos
    private int x, y;      // Die Position des Tetrominos

    // Konstruktor: Initialisiert den Tetromino mit einer gegebenen Form und Farbe
    public Tetromino(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        x = 0; // Startposition x
        y = 0; // Startposition y
    }

    // Gibt die Form des Tetrominos zurück
    public int[][] getShape() {
        return shape;
    }

    // Gibt die Farbe des Tetrominos zurück
    public Color getColor() {
        return color;
    }

    // Gibt die x-Position des Tetrominos zurück
    public int getX() {
        return x;
    }

    // Gibt die y-Position des Tetrominos zurück
    public int getY() {
        return y;
    }

    // Bewegt den Tetromino nach links
    public void moveLeft() {
        x--;
    }

    // Bewegt den Tetromino nach rechts
    public void moveRight() {
        x++;
    }

    // Bewegt den Tetromino nach unten
    public void moveDown() {
        y++;
    }

    // Rotiert den Tetromino um 90 Grad im Uhrzeigersinn
    public void rotate() {
        int[][] rotatedShape = new int[shape[0].length][shape.length];
    
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                rotatedShape[j][shape.length - 1 - i] = shape[i][j];
            }
        }
    
        shape = rotatedShape; // Setzt die Form auf die rotierte Form
    }
}
