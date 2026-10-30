package Classes;

import java.io.IOException;

/**
 * Class for game logic
 */
public class Game {

    private final Computer computer;
    private final Player player;
    private final Pack pack;
    private final Console console;
    private final Files files;

    /**
     * This constructor initializes all objects important for the game
     */
    public Game() {
        pack = new Pack();
        if (!pack.addCardsFromFile(Files.CARDS_FILE)) {
            throw new IllegalStateException("Cannot start the game, the cards were not loaded.");
        }

        player = new Player();
        computer = new Computer();

        player.setPack(pack);
        computer.setPack(pack);
        player.setOpponent(computer);
        computer.setOpponent(player);

        player.setTurn(true);
        computer.setTurn(false);

        pack.dealStartCards(player, computer);
        pack.initializeMelded();

        files = new Files(computer, player, pack);
        console = new Console(pack, player, computer, files);
    }

    /**
     * Method controls whether Player or Computer have empty pack
     * It sets gameIsOver to true and prints the winner
     */
    private boolean checkWinner() {
        if (player.getHand().isEmpty()) {
            console.setGameIsOver(true);
            System.out.println("\n\n🎉Player is the winner🎂");
            return true;
        } else if (computer.getHand().isEmpty()) {
            console.setGameIsOver(true);
            System.out.println("\n\n✨Computer is the winner✨");
            return true;
        }
        return false;
    }

    /**
     * Method prints the state of the game before each turn
     */
    private void printStatus() {
        System.out.println("\nRemaining in the pack: " + pack.cardPack.size() + " cards");
        System.out.println("Computer has: " + computer.getHand().size() + " cards");
        System.out.println("Actual color: " + pack.getActualCardColor() + " | Actual type: " + pack.getActualCardType());
        if (player.isTurn()) {
            player.getplayersCards();
        }
    }

    /**
     * Method contains game logic
     * Card effects are applied in Pack.playCard(), so here it only controls who plays and if somebody won
     * (throws IOException is kept only because of Main)
     */
    public void play() throws IOException {
        files.manual(Files.MANUAL_FILE);

        while (!console.isGameIsOver()) {
            printStatus();

            if (computer.isTurn()) {
                computer.playCard();
            } else {
                console.start();
            }

            checkWinner();
        }
        System.out.println("\n\n\n🎆Game over🎆");
    }
}