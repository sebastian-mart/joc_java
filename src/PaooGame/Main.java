package PaooGame;

import javax.swing.*;
import javax.xml.crypto.Data;

import PaooGame.GameWindow.GameWindow;

public class Main
{
    public static void main(String[] args)
    {
        //Game paooGame = new Game("PaooGame", 800, 600);
        Game paooGame =Game.GetInstance("Game",64*20,960);

        paooGame.StartGame();
    }
}
