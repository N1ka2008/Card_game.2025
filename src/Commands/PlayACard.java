package Commands;
import Classes.Player;
import Classes.Computer;
import Classes.Pack;
import Classes.CardColor;
import Classes.CardType;
import Classes.Card;

/**
 * Class for command play card
 */
public class PlayACard extends Command {

    private Player player;
    private Computer computer;
    private Pack pack;

    public PlayACard(Player player, Computer computer, Pack pack) {
        this.player = player;
        this.computer = computer;
        this.pack = pack;
    }

    /**
     * Method splits argument into color and type. Controls if player has this card and if it can be played.
     * The turn changes only if the card was really played (Pack.playCard() passes the turn and applies the effect)
     * Type can be written as a name or as a digit, e.g. play FALLING 3
     */
    @Override
    public boolean execute(String argument) {
        String[] parts = argument.split(" ");
        if (parts.length != 2) {
            System.out.println("Invalid format! Try: play COLOR TYPE");
            return false;
        }

        CardColor cardColor;
        CardType cardType;
        try {
            cardColor = CardColor.valueOf(parts[0].toUpperCase());
            cardType = CardType.parse(parts[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid card! Check color and type");
            return false;
        }

        Card card = player.findCard(cardColor, cardType);
        if (card == null) {
            System.out.println("You dont have this card");
            return false;
        }

        if (!pack.canPlay(card)) {
            System.out.println("You cannot play this card. It must have the color "
                    + pack.getActualCardColor() + " or the type " + pack.getActualCardType());
            return false;
        }

        System.out.println("Player played: " + card);
        pack.playCard(player, card);
        return true;
    }

    public boolean exit() {
        return false;
    }
}
