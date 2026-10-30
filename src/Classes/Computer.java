package Classes;

/**
 * Class for computer and its methods, extends Participant
 */
public class Computer extends Participant {

    public Computer() {
        super("Computer");
    }

    /**
     * This method controls which cards can computer play and then plays them.
     * The effect of the card is applied in Pack.playCard()
     */
    public void playCard() {
        System.out.println("\nComputer's turn");
        boolean hasPlayedCard = false;

        for (Card card : hand) {
            if (pack.canPlay(card)) {
                System.out.println("Computer played: " + card.toString());
                pack.playCard(this, card);
                hasPlayedCard = true;
                break;
            }
        }

        if (!hasPlayedCard) {
            if (drawCard() != null) {
                System.out.println("computer drew a card");
            } else {
                System.out.println("computer cannot draw a card");
            }
            pack.passTurn(this);
        }
    }

    /**
     * This method allows Computer to choose the new color (AWAKENING card)
     * first it counts which color is the most and then returns it
     */
    @Override
    public CardColor chooseColor() {
        int fallingCount = 0;
        int chaseCount = 0;
        int flyingCount = 0;
        int searchCount = 0;

        for (Card card : hand) {
            switch (card.getColor()) {
                case FALLING:
                    fallingCount++;
                    break;
                case CHASE:
                    chaseCount++;
                    break;
                case FLYING:
                    flyingCount++;
                    break;
                case SEARCH:
                    searchCount++;
                    break;
            }
        }

        int max = Math.max(Math.max(fallingCount, chaseCount), Math.max(flyingCount, searchCount));

        CardColor newColor = pack.getActualCardColor();
        if (max == 0) {
            newColor = pack.getActualCardColor();
        } else if (max == fallingCount) {
            newColor = CardColor.FALLING;
        } else if (max == chaseCount) {
            newColor = CardColor.CHASE;
        } else if (max == flyingCount) {
            newColor = CardColor.FLYING;
        } else if (max == searchCount) {
            newColor = CardColor.SEARCH;
        }

        return newColor;
    }
}