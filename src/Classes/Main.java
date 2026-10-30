package Classes;

import java.io.IOException;

/**
 * Class launches the game
 */
public class Main {
    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.play();
        } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }
}