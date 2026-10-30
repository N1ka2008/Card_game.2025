package Classes;

/**
 * Class for player and its methods, extends Participant
 */
public class Player extends Participant {

    public Player() {
        super("Player");
    }

    /**
     * Method allows player to draw a card
     */
    public String playerDrawCard() {
        Card card = drawCard();
        if (card == null) {
            return "There is no card to draw";
        }
        return "player drew a card: " + card;
    }

    /**
     * Method gives player the option to choose the new color (AWAKENING card)
     * It asks until the player types a valid color
     */
    @Override
    public CardColor chooseColor() {
        while (true) {
            System.out.println("Type a color you want to change to (FALLING, CHASE, FLYING, SEARCH): ");
            if (!Console.scanner.hasNextLine()) {
                return CardColor.values()[0];
            }
            String input = Console.scanner.nextLine().trim().toUpperCase();
            try {
                return CardColor.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid color!");
            }
        }
    }

    /**
     * Method prints all cards player has in his pack
     */
    public void getplayersCards() {
        System.out.println("\nPlayers cards: ");
        for (Card card : hand) {
            System.out.println("[" + card.getColor() + " | " + card.getType() + "]");
        }
        System.out.println("player has: " + hand.size() + " cards");
    }
}
