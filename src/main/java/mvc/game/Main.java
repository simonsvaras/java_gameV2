package mvc.game;

import mvc.game.controller.C_GamePanel;
import org.game.main.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventures");

        C_GamePanel gamePanel = new C_GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);


        // We want to call set object method before game

        gamePanel.setupGame();
        gamePanel.startGame();
    }
}
