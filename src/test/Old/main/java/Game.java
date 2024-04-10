package src.main.java;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel {
    private List<Tetromino> tetrominos; // Liste der Tetrominos
    private int currentX; // Die x-Position des aktuellen Tetrominos im Spielfeld
    private int currentY; // Die y-Position des aktuellen Tetrominos im Spielfeld
    private Block[][] gameBoard; // Das Spielfeld, das die Position der Blöcke speichert
    private int blockSize; // Die Größe der Blöcke

    public Game() {
        tetrominos = new ArrayList<>(); // Liste für Tetrominos initialisieren
        currentX = 5; // Anfangs-X-Position
        currentY = 0; // Anfangs-Y-Position
        blockSize = 20; // Größe der Blöcke
        gameBoard = new Block[10][20]; // Spielfeld mit 10 Spalten und 20 Reihen initialisieren

        // Das Spielfeld initialisieren und alle Werte auf null (leer) setzen
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                gameBoard[i][j] = null;
            }
        }

        // Fügen Sie das erste Tetromino zur Liste hinzu
        tetrominos.add(TetrominoFactory.getRandomTetromino());
    }

    public Tetromino getCurrentTetromino() {
        return tetrominos.get(tetrominos.size() - 1); // Das aktuelle Tetromino ist das letzte in der Liste
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void moveLeft() {
        if (isMoveValid(getCurrentTetromino(), currentX - 1, currentY)) {
            currentX--;
        }
    }

    public void moveRight() {
        if (isMoveValid(getCurrentTetromino(), currentX + 1, currentY)) {
            currentX++;
        }
    }

    public void moveDown() {
        if (isMoveValid(getCurrentTetromino(), currentX, currentY + 1)) {
            currentY++;
        } else {
            Tetromino x = getCurrentTetromino();
            addToGameBoard(x, currentX, currentY);
            currentX = 5;
            currentY = 0;
            tetrominos.add(TetrominoFactory.getRandomTetromino());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                if (gameBoard[x][y] != null) {
                    drawBlock(x, y, blockSize, g, gameBoard[x][y].getColor());
                }
            }
        }

        for (Tetromino tetromino : tetrominos) {
            drawTetromino(tetromino, currentX, currentY, blockSize, g);
        }
    }

    public void drawTetromino(Tetromino tetromino, int x, int y, int blockSize, Graphics g) {
        int[][] shape = tetromino.getShape();
        Color color = tetromino.getColor();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int blockX = x + col;
                    int blockY = y + row;
                    drawBlock(blockX, blockY, blockSize, g, color);
                }
            }
        }
    }

    public void drawBlock(int x, int y, int blockSize, Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);
        g.setColor(Color.BLACK);
        g.drawRect(x * blockSize, y * blockSize, blockSize, blockSize);
    }

    public void rotate() {
        Tetromino rotatedTetromino = rotateTetromino(getCurrentTetromino());
        if (isMoveValid(rotatedTetromino, currentX, currentY)) {
            tetrominos.set(tetrominos.size() - 1, rotatedTetromino); // Aktuelles Tetromino ersetzen
        }
    }

    private Tetromino rotateTetromino(Tetromino tetromino) {
        int[][] oldShape = tetromino.getShape();
        int[][] newShape = new int[oldShape[0].length][oldShape.length];
        for (int i = 0; i < oldShape.length; i++) {
            for (int j = 0; j < oldShape[0].length; j++) {
                newShape[j][oldShape.length - 1 - i] = oldShape[i][j];
            }
        }
        return new Tetromino(newShape, tetromino.getColor());
    }

    private void addToGameBoard(Tetromino tetromino, int x, int y) {
        int[][] shape = tetromino.getShape();
        Color color = tetromino.getColor();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = x + col;
                    int boardY = y + row;
                    gameBoard[boardX][boardY] = new Block(color);
                }
            }
        }
    }

    private boolean isMoveValid(Tetromino tetromino, int x, int y) {
        int[][] shape = tetromino.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = x + col;
                    int boardY = y + row;
                    if (boardX < 0 || boardX >= 10 || boardY >= 20 || gameBoard[boardX][boardY] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
