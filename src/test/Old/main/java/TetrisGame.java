package src.main.java;

import java.awt.Color;

import javax.swing.JFrame;

public class TetrisGame {
    public static void main(String[] args) {
        // Erstelle ein JFrame, um das Tetris-Spiel darzustellen
        JFrame tetrisFrame = new JFrame("Tetris");

        // Setze die Größe des JFrame-Fensters
        tetrisFrame.setSize(400, 450);

        // Beende das Programm, wenn das Fenster geschlossen wird
        tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Erstelle ein Objekt der Klasse Board (Spielfeld)
        Board board = new Board();

        // Füge das Spielfeld dem JFrame hinzu
        tetrisFrame.add(board);

        // Mache das JFrame-Fenster sichtbar
        tetrisFrame.setVisible(true);
        tetrisFrame.setBackground(Color.BLUE); // Ändert die Hintergrundfarbe auf Blau
    }
}
