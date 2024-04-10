package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements KeyListener {
    private Game game;

    public Board() {
        // Erstellen Sie ein Spiel-Objekt
        game = new Game();

        // Fügen Sie den KeyListener (für Tastatureingaben) zum Spielfeld hinzu
        addKeyListener(this);

        // Setzen Sie den Fokus auf das Spielfeld
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Zeichnen Sie den aktuellen Tetromino auf dem Spielfeld
        Tetromino currentTetromino = game.getCurrentTetromino();
        int currentX = game.getCurrentX();
        int currentY = game.getCurrentY();
        int blockSize = game.getBlockSize();

        drawTetromino(currentTetromino, currentX, currentY, blockSize, g);
    }

    // Zeichnen Sie den Tetromino auf dem Spielfeld
    public void drawTetromino(Tetromino tetromino, int x, int y, int blockSize, Graphics g) {
        int[][] shape = tetromino.getShape();
        Color color = tetromino.getColor();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    g.setColor(color);
                    g.fillRect((x + col) * blockSize, (y + row) * blockSize, blockSize, blockSize);
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
            game.rotate();
        }
        repaint(); // Aktualisieren Sie das Spielfeld nach Tastatureingaben
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

            // Fügen Sie das Spielfeld zum JFrame hinzu
            tetrisFrame.add(board);
            tetrisFrame.pack();
            tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tetrisFrame.setVisible(true);
            //tetrisFrame.setBackground(Color.BLUE); // Ändert die Hintergrundfarbe auf Blau
            board.requestFocusInWindow(); // Setzen Sie den Fokus auf das Spielfeld
        });
    }
}
