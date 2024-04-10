package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener {
    private Game game;

    public Board() {
        // Erstelle ein Spiel-Objekt
        game = new Game();

        // Fügen den KeyListener (für Tastatureingaben) zum Spielfeld hinzu
        addKeyListener(this);

        // Setze den Fokus auf das Spielfeld
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // Starte das Spiel
        startGame();
    }

    private void startGame() {
        // Erstelle einen Timer, um das Spiel zu aktualisieren
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.moveDown(); // Bewegung des Tetrominos nach unten
                repaint(); // Aktualisiere das Spielfeld
            }
        });

        // Starte den Timer
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Zeichne Spielfeld und die Tetrominos
        drawGame(g);
    }

    // Zeichne das Spielfeld und die Tetrominos
    private void drawGame(Graphics g) {
        int blockSize = game.getBlockSize();

        // Zeichne das Spielfeld 10 Breite und 20 Höhe
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                if (game.getBlock(x, y) != null) {
                    g.setColor(game.getBlock(x, y).getColor());
                    g.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);
                }
            }
        }

        // Zeichne die Tetrominos
        Tetromino currentTetromino = game.getAktuellesTetromino();
        int currentX = game.getCurrentX();
        int currentY = game.getCurrentY();

        int[][] shape = currentTetromino.getShape();
        Color color = currentTetromino.getColor();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    g.setColor(color);
                    g.fillRect((currentX + col) * blockSize, (currentY + row) * blockSize, blockSize, blockSize);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            game.moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            game.moveRight();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            game.moveDown();
        } else if (keyCode == KeyEvent.VK_UP) {
            game.drehen();
        }
        repaint(); // Aktualisiere das Spielfeld nach Tastatureingaben
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nicht verwendet
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Nicht verwendet
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame tetrisFrame = new JFrame("Tetris");
            Board board = new Board();

            // Füge das Spielfeld zum JFrame hinzu
            tetrisFrame.add(board);
            tetrisFrame.pack();
            tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tetrisFrame.setVisible(true);
            board.requestFocusInWindow(); // Setze den Fokus auf das Spielfeld
        });
    }
}
