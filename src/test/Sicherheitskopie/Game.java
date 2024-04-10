/* 
 * Lizenzhinweis Laut BSI
 * Autor: Adrian Waletzki
 * E-Mail: a.waletzki@web.de
 * Weitere Informationen zur BSI-Lizenz finden Sie unter:
 * https://www.bsi.bund.de/bsi-lizenz
 */

package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel implements KeyListener{
    private List<Tetromino> tetrominos; // Liste der Tetrominos
    private int currentX; // Die x-Position des aktuellen Tetrominos im Spielfeld
    private int currentY; // Die y-Position des aktuellen Tetrominos im Spielfeld
    private Block[][] gameBoard; // Das Spielfeld, das die Position der Blöcke speichert
    private int blockSize; // Die Größe der Blöcke
    private int xOffset = 5; // Verschiebung nach rechts
    private int yOffset = 5; // Verschiebung nach unten
    private int score = 0; // Punktzahl
    private int rowdel = 0; // Zeilen
    private int lvl = 1; // Lvl
    private Timer timer;

    public Game() {
        // Erstelle einen Timer, um das Spiel zu aktualisieren
        //istSpielBeendet();
        timer = new Timer(500 * lvl, e -> {
            if (!istSpielBeendet()) {
                moveDown(); // Bewegung des Tetrominos nach unten
                repaint(); // Aktualisiere das Spielfeld
            } else {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Vielen Dank fürs Spielen! \n Spiel beendet! \n Ihr Highscore: "+score); // Beispiel für eine Meldung
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose(); // Schließen Sie das Fenster
                System.out.println("Highscore: " + score); // Ausgabe der aktuellen Punktzahl
                System.out.println("Vielen Dank fürs Spielen!"); // Ausgabe der aktuellen Punktzahl
            }
        });
          
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

        // Füge das erste Tetromino zur Liste hinzu
        tetrominos.add(TetrominoFactory.getRandomTetromino());

        // Füge den KeyListener hinzu
        addKeyListener(this);

        // Setze den Fokus auf das Spielfeld
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        startGame();
    }

    private void updateLevel(){

        if(rowdel <= 1){
            lvl = 1;
            System.out.println("Aktulles Level: "+lvl);
        }else if(rowdel <= 2){
            lvl = 2;
            System.out.println("Aktulles Level: "+lvl);
        }else if(rowdel <= 4){
            lvl = 3;
            System.out.println("Aktulles Level: "+lvl);
        }else if(rowdel <= 7){
            lvl = 4;
            System.out.println("Aktulles Level: "+lvl);
        }else if(rowdel <= 10){
            lvl = 5;
            System.out.println("Aktulles Level: "+lvl);
        }
        // Hier Timer anhalten und neu konfigurieren
        timer.stop();
        timer.setDelay(500 - (lvl * 80));
        timer.start();
    }

    private void startGame() {
        
        System.out.println("Willkommen zu Tetris"); // Ausgabe der aktuellen Punktzahl (optional)
        System.out.println("---- Steuerung -----");
        System.out.println("< Bewegung nach Links");
        System.out.println("> Bewegung nach Rechts");
        System.out.println("v Bewegung nach Unten");
        System.out.println("^ Drehen");
        System.out.println("--------------------");
        System.out.println("Viel Vergnügen");
        System.out.println("Aktulles Level: "+ lvl);

        // Starte den Timer
        timer.start();
    }

    private boolean istSpielBeendet() {
        Tetromino currentTetromino = getAktuellesTetromino();
        int[][] shape = currentTetromino.getShape();
        
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = currentX + col;
                    int boardY = currentY + row;
    
                    // Überprüfen, ob die Position des aktuellen Tetrominos auf dem Spielfeld besetzt ist
                    if (boardX < 0 || boardX >= 10 || boardY >= 20 || (boardY >= 0 && gameBoard[boardX][boardY] != null)) {
                        timer.stop(); // Timer anhalten
                        return true; // Kollision gefunden, das Spiel ist beendet
                    }
                }
            }
        }
        return false; // Keine Kollision gefunden, das Spiel geht weiter
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Zeichne den Rahmen und das Spielfeld
        drawFrame(g, xOffset, yOffset);
        drawGame(g, xOffset, yOffset);
        
        Color dunkel = new Color(117,117,117);
        setBackground(dunkel);
        //setBackground(Color.LIGHT_GRAY);
        // Zeichne das Raster
        Color dunkelblau = new Color(0, 26, 63);
        g.setColor(dunkelblau);// Farbe des Rasters
        int fieldWidth = 10 * blockSize;
        int fieldHeight = 20 * blockSize;
    
        // Zeichne das Raster
        for (int x = xOffset * blockSize; x <= fieldWidth + xOffset * blockSize; x += blockSize) {
            g.drawLine(x, yOffset * blockSize, x, fieldHeight + yOffset * blockSize);
        }
        for (int y = yOffset * blockSize; y <= fieldHeight + yOffset * blockSize; y += blockSize) {
            g.drawLine(xOffset * blockSize, y, fieldWidth + xOffset * blockSize, y);
        }
    }    
    
    // Methode zum Zeichnen des Rahmens
    private void drawFrame(Graphics g, int xOffset, int yOffset) {
        Color dunkelblau = new Color(4, 15, 143);
        g.setColor(dunkelblau);
        int borderWidth = 10;
        int fieldWidth = 10 * blockSize; // Verwenden Sie 10, da das Spielfeld 10 Spalten hat
        int fieldHeight = 20 * blockSize; // Verwenden Sie 20, da das Spielfeld 20 Zeilen hat

        g.fillRect(xOffset * blockSize, yOffset * blockSize -10, fieldWidth + borderWidth, borderWidth); // Oberer Rahmen
        g.fillRect(xOffset * blockSize -10, yOffset * blockSize -10, borderWidth, fieldHeight + borderWidth +10); // Linker Rahmen
        g.fillRect(xOffset * blockSize, fieldHeight + yOffset * blockSize, fieldWidth + borderWidth, borderWidth); // Unterer Rahmen
        g.fillRect(fieldWidth + xOffset * blockSize, yOffset * blockSize, borderWidth, fieldHeight + borderWidth); // Rechter Rahmen
    }


    public Block getBlock(int x, int y) {
        return gameBoard[x][y];
    }
        
    private void drawGame(Graphics g, int xOffset, int yOffset) {
        // Zeichne das Spielfeld
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                if (gameBoard[x][y] != null) {
                    g.setColor(gameBoard[x][y].getColor());
                    g.fillRect((x + xOffset) * blockSize, (y + yOffset) * blockSize, blockSize, blockSize);
                }
            }
        }
    
        // Zeichne die Tetrominos
        Tetromino currentTetromino = getAktuellesTetromino();
    
        int[][] shape = currentTetromino.getShape();
        Color color = currentTetromino.getColor();
    
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    g.setColor(color);
                    g.fillRect((currentX + col + xOffset) * blockSize, (currentY + row + yOffset) * blockSize, blockSize, blockSize);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveRight();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveDown();
        } else if (keyCode == KeyEvent.VK_UP) {
            drehen();
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

    public Tetromino getAktuellesTetromino() {
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
        if (istBewegungGueltig(getAktuellesTetromino(), currentX - 1, currentY)) {
            currentX--;
        }
    }

    public void moveRight() {
        if (istBewegungGueltig(getAktuellesTetromino(), currentX + 1, currentY)) {
            currentX++;
        }
    }

    public void moveDown() {
        if (istBewegungGueltig(getAktuellesTetromino(), currentX, currentY + 1)) {
            currentY++;
        } else {
            handleCollision();
        }
    }

    public void drehen() {
        Tetromino aktuelles = getAktuellesTetromino();
        Tetromino gedreht = new Tetromino(rotiereMatrix(aktuelles.getShape()), aktuelles.getColor());

        if (istBewegungGueltig(gedreht, currentX, currentY)) {
            tetrominos.set(tetrominos.size() - 1, gedreht); // Aktualisieren das aktuelle Tetromino
        }
    }

    private int[][] rotiereMatrix(int[][] matrix) {
        int zeilen = matrix.length;
        int spalten = matrix[0].length;
        int[][] gedreht = new int[spalten][zeilen];

        for (int i = 0; i < zeilen; i++) {
            for (int j = 0; j < spalten; j++) {
                gedreht[j][zeilen - 1 - i] = matrix[i][j];
            }
        }
        return gedreht;
    }

    private void handleCollision() {
        Tetromino currentTetromino = getAktuellesTetromino();
        int[][] shape = currentTetromino.getShape();

        // Platziere die Blöcke des aktuellen Tetrominos auf dem Spielfeld
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = currentX + col;
                    int boardY = currentY + row;
                    gameBoard[boardX][boardY] = new Block(currentTetromino.getColor());
                }
            }
        }
        // Überprüfen auf vollständige Reihen und löschen Sie sie
        removeFullRows();

        // Füge dann ein neues Tetromino zur Liste hinzu und setze seine Position zurück
        tetrominos.add(TetrominoFactory.getRandomTetromino());
        currentX = 5; // Setze die X-Position zurück
        currentY = 0; // Setze die Y-Position zurück
    }

    private void removeFullRows() {
        int rowsDeleted = 0; // Anzahl der gelöschten Reihen
        for (int y = 19; y >= 0; y--) {
            boolean isFull = true;
            for (int x = 0; x < 10; x++) {
                if (gameBoard[x][y] == null) {
                    isFull = false;
                    break;
                }
            }

            if (isFull) {
                rowsDeleted++;
                // Lösche die volle Reihe
                for (int j = y; j > 0; j--) {
                    for (int x = 0; x < 10; x++) {
                        gameBoard[x][j] = gameBoard[x][j - 1];
                    }
                }
                // Füge eine leere Zeile oben ein
                for (int x = 0; x < 10; x++) {
                    gameBoard[x][0] = null;
                }
                y++;
            }
        }

        // Aktualisiere die Punktzahl basierend auf der Anzahl der gelöschten Reihen
        if (rowsDeleted > 0) {
            switch (rowsDeleted) {
                case 1:
                    score += 10 * lvl;
                    break;
                case 2:
                    score += 25 * lvl;
                    break;
                case 3:
                    score += 40 * lvl;
                    break;
                case 4:
                    score += 50 * lvl;
                    break;
                default:
                    break;
            }
                rowdel += rowsDeleted;
                System.out.println("Punktzahl: " + score); // Ausgabe der aktuellen Punktzahl
                updateLevel();
        } 
    }
    

    private boolean istBewegungGueltig(Tetromino tetromino, int x, int y) {
        int[][] shape = tetromino.getShape();

        // Überprüfe, ob die Tetromino-Bewegung außerhalb des Spielfelds liegt
        if (x < 0 || x + shape[0].length > 10 || y + shape.length > 20) {
            return false;
        }

        // Überprüfe, ob es eine Kollision mit vorhandenen Blöcken auf dem Spielfeld gibt
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = x + col;
                    int boardY = y + row;

                    // Überprüfe, ob die Position besetzt ist
                    if (gameBoard[boardX][boardY] != null) {
                        return false;
                    }
                }
            }
        }
        return true; // Die Bewegung ist gültig
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame tetrisFrame = new JFrame("Tetris");
            Game game = new Game();

            // Füge das Spielfeld zum JFrame hinzu
            tetrisFrame.add(game);
            tetrisFrame.pack();
            tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tetrisFrame.setVisible(true);
            game.requestFocusInWindow(); // Setzen Sie den Fokus auf das Spielfeld
        });
    }
}
