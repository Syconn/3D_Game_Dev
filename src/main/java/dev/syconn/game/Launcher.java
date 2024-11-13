package dev.syconn.game;

import dev.syconn.game.core.GameEngine;
import dev.syconn.game.core.Window;
import dev.syconn.game.test.TestGame;

import static dev.syconn.game.util.Constants.TITLE;

public class Launcher {

    private static Window window;
    private static TestGame game;

    public static void main(String[] args) {
        window = new Window(TITLE, 1600, 900, false);
        game = new TestGame();
        GameEngine engine = new GameEngine();

        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Window getWindow() {
        return window;
    }

    public static TestGame getGame() {
        return game;
    }
}
