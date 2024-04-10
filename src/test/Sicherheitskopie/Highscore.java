package src.main.java;

import javax.swing.*;
import java.awt.*;

public class Highscore extends JLabel {
    private int score = 0; // Punktzahl

    public Highscore() {
        setFont(new Font("Arial", Font.BOLD, 20));
        setForeground(Color.WHITE);
        updateScoreLabel();
    }

    public int getScore() {
        return score;
    }

    public void updateScoreLabel() {
        setText("Punktzahl: " + score);
    }

    public void addScore(int points) {
        score += points;
        updateScoreLabel();
        fireScoreUpdated(); // Benachrichtige über die Aktualisierung der Punktzahl
    }

    public void resetScore() {
        score = 0;
        updateScoreLabel();
        fireScoreUpdated(); // Benachrichtige über die Aktualisierung der Punktzahl
    }

    public interface ScoreListener {
        void scoreUpdated(int newScore);
    }
    
    private ScoreListener scoreListener;
    
    public void setScoreListener(ScoreListener listener) {
        this.scoreListener = listener;
    }
    
    private void fireScoreUpdated() {
        if (scoreListener != null) {
            scoreListener.scoreUpdated(score);
        }
    }
    
}