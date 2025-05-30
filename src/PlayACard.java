/**
 * Class for command play card
 */
public class PlayACard extends Command{

    private Player player;
    private Computer computer;

    public PlayACard(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
    }

    /**
     * Method splits argument into color and type. Controls if player has this card, if does it launch playerPlayCard
     * It also changes turn
     */
    @Override
    public boolean execute(String argument) {
        String[] parts = argument.split(" ");
        if (parts.length != 2) {
            System.out.println("Invalid format! Try: play COLOR TYPE");
            return false;
        }

        String color = parts[0].toUpperCase();
        String type = parts[1].toUpperCase();

        try {
            CardColor cardColor = CardColor.valueOf(color);
            CardType cardType = CardType.valueOf(type);

            boolean hasCard = false;
            for (Card card : player.playerPack) {
                if (card.getColor() == cardColor && card.getType() == cardType) {
                    hasCard = true;
                    break;
                }
            }

            if (!hasCard) {
                System.out.println("You dont have this card");
                return false;
            }

            System.out.println(player.playerPlayCard(color, type));
            player.setTurn(false);
            computer.setTurn(true);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid card! Check color and type");
            return false;
        }
    }

    public boolean exit() {
        return false;
    }
}
