/* 
 * Lizenzhinweis Laut BSI
 * Autor: Adrian Waletzki
 * E-Mail: a.waletzki@web.de
 * Weitere Informationen zur BSI-Lizenz finden Sie unter:
 * https://www.bsi.bund.de/bsi-lizenz
 */

package src.main.java;

import javax.swing.JFrame;


public class TetrisGame {
    public static void main(String[] args) {
        // Erstelle ein JFrame, um das Tetris-Spiel darzustellen
        JFrame tetrisFrame = new JFrame("Tetris");

        // Setze die Größe des JFrame-Fensters
        tetrisFrame.setSize(450, 550);

        // Beende das Programm, wenn das Fenster geschlossen wird
        tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Erstelle ein Objekt der Klasse Game
        Game game = new Game();

        // Füge das Spielfeld dem JFrame hinzu, nicht dem Game-Panel
        tetrisFrame.add(game);

        // Setze den Fokus auf das Spielfeld
        game.requestFocusInWindow();

        // Mache das JFrame-Fenster sichtbar
        tetrisFrame.setVisible(true);
    }
}