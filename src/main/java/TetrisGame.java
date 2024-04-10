package src.main.java;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TetrisGame {
    public static void main(String[] args) {
        // Starte das Spiel innerhalb des Event-Dispatch-Threads
        SwingUtilities.invokeLater(() -> {
            showWelcomeDialog(); // Zeige den Willkommensdialog
        });
    }

    private static void showWelcomeDialog() {
        // Zeige einen Willkommensdialog mit Steuerungshinweisen
        JOptionPane.showMessageDialog(null,
            "Willkommen zu Tetris\n"
            + "---- Steuerung -----\n"
            + "< Bewegung nach Links\n"
            + "> Bewegung nach Rechts\n"
            + "v Bewegung nach Unten\n"
            + "^ Drehen\n"
            + "--------------------\n"
            + "Viel Spass\n"
            + "Aktuelles Level: 1"
        );

        // Erstelle das JFrame für das Tetris-Spiel
        JFrame tetrisFrame = new JFrame("Tetris");
        tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Setze die Größe des JFrame-Fensters
        tetrisFrame.setSize(450, 550); // Hier ändern Sie die Fenstergröße auf Ihre gewünschten Werte

        // Erstelle ein Objekt der Klasse Game
        Game game = new Game();

        // Füge das Spielfeld dem JFrame hinzu
        tetrisFrame.add(game);

        // Setze den Fokus auf das Spielfeld
        game.requestFocusInWindow();

        // Mache das JFrame-Fenster sichtbar
        tetrisFrame.setVisible(true);
    }
}
