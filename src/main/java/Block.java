/* 
 * Lizenz: BSD-3-Clause
 * Autor: Adrian Waletzki
 * Version 1.0
 * Datum: 06.11.2023
 * E-Mail: a.waletzki@web.de
 * https://opensource.org/license/bsd-3-clause/
 */

package src.main.java;

import java.awt.Color;

public class Block {
    // Farben für Tetrominos
    public static final Color RZ_COLOR = new Color(69, 194, 189);   // turki 
    public static final Color I_COLOR = new Color(0, 69, 255);    // blue 
    public static final Color O_COLOR = new Color(255, 96, 94);     // lachs
    public static final Color Z_COLOR = new Color(252, 255, 75);    // yellow 
    public static final Color L_COLOR = new Color(116, 27, 124);   // violet 
    public static final Color RL_COLOR = new Color(235, 147, 43);   // orange 
    public static final Color NB_COLOR = new Color(199,66, 235);   // pink 
    

    // Formen für Tetrominos
    public static final int[][] L_BLOCK = {
        {1, 0},
        {1, 0},
        {1, 1}
    };

    public static final int[][] I_BLOCK = {
        {1},
        {1},
        {1},
        {1}
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

    public static final int[][] NB_BLOCK = {
        {0, 1, 0},
        {1, 1, 1}
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
