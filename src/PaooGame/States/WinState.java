package PaooGame.States;

import PaooGame.Database;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class WinState extends State {
    private String playerName = "";
    private boolean nameEntered = false;
    private BufferedImage buffer;
    private Graphics bufferGraphics;
    private int score;

    public WinState(RefLinks refLink) {
        super(refLink);
        this.score = refLink.GetHero().getScore();

        // Inițializare buffer pentru dublu buffering
        buffer = new BufferedImage(refLink.GetWidth(), refLink.GetHeight(), BufferedImage.TYPE_INT_ARGB);
        bufferGraphics = buffer.getGraphics();

        InitHandlers();
    }

    @Override
    public void InitHandlers() {
        Canvas canvas = refLink.GetGame().GetGameWindow().GetCanvas();
        canvas.setFocusable(true);
        canvas.requestFocusInWindow();

        // Ștergem listenerii vechi
        for (KeyListener kl : canvas.getKeyListeners()) {
            canvas.removeKeyListener(kl);
        }

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!nameEntered) {
                    char c = e.getKeyChar();

                    if ((c == '\n' || c == '\r')&&!playerName.isEmpty()) { // Enter
                        saveScoreAndExit();
                    } else if (c == '\b') { // Backspace
                        if (!playerName.isEmpty()) {
                            playerName = playerName.substring(0, playerName.length() - 1);
                        }
                    } else if (Character.isLetterOrDigit(c) || c == ' ') {
                        if (playerName.length() < 12) {
                            playerName += c;
                        }
                    }
                    redrawBuffer();
                }
            }

        });
    }

    private void saveScoreAndExit() {
        if (playerName.isEmpty()) {
            playerName = "Ann";
        }

        new Database().insertHighScore(playerName, score);
        nameEntered = true;

        // Curățăm listenerii
        Canvas canvas = refLink.GetGame().GetGameWindow().GetCanvas();
        for (KeyListener kl : canvas.getKeyListeners()) {
            canvas.removeKeyListener(kl);
        }

        // Schimbăm starea și resetăm jocul
        ((MenuState)refLink.GetGame().GetMenuState()).createNewGameSave();
        State.SetState(refLink.GetGame().GetMenuState());
    }

    private void redrawBuffer() {
        // Redesenăm în buffer
        bufferGraphics.setColor(new Color(0, 0, 0, 200));
        bufferGraphics.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());

        bufferGraphics.setColor(Color.GREEN);
        bufferGraphics.setFont(new Font("Arial", Font.BOLD, 64));
        String text = "VICTORIE!";
        int textWidth = bufferGraphics.getFontMetrics().stringWidth(text);
        bufferGraphics.drawString(text, (buffer.getWidth() - textWidth)/2, 150);

        if (!nameEntered) {
            bufferGraphics.setColor(Color.WHITE);
            bufferGraphics.setFont(new Font("Arial", Font.PLAIN, 24));
            bufferGraphics.drawString("Introdu numele pentru scor:", buffer.getWidth()/2 - 120, 250);

            bufferGraphics.setColor(Color.YELLOW);
            bufferGraphics.fillRect(buffer.getWidth()/2 - 150, 280, 300, 40);
            bufferGraphics.setColor(Color.BLACK);
            bufferGraphics.drawString(playerName + (System.currentTimeMillis() % 1000 < 500 ? "|" : ""),
                    buffer.getWidth()/2 - 140, 310);
        }
    }

    @Override
    public void Update() {
        // Nu este necesară actualizare
    }

    @Override
    public void Draw(Graphics g) {
        // Desenăm bufferul pe ecran
        g.drawImage(buffer, 0, 0, null);

        // Forțează o redesenare continuă
        if (!nameEntered) {
            redrawBuffer();
        }
    }
}