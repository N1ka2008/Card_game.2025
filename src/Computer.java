import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Computer extends Pack implements Serializable {

    private boolean turn;
    Player player;

    public Computer() {
    }

    public Computer(ArrayList<Card> cardPack, Card melded, Player player) {
        super(cardPack, melded);
        this.player = player;
    }

    public ArrayList<Card> getComputerPack() {
        return computerPack;
    }

    public ArrayList<Card> computerPack = new ArrayList<>();

    /**
     * This method controls which cards can computer play and then plays them.
     * It also applies methods DiamondsK(), changeColor() and drawCard()
     * Iterator<Card>, iterator.hasNext() and iterator.next() is not mine. Source: Chat gpt
     * Rest of the method is mine
     */
    public void playCard() {
        System.out.println("\nComputer's turn");
        boolean hasPlayedCard = false;

        for (Iterator<Card> iterator = computerPack.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getColor().equals(getActualCardColor()) || card.getType().equals(getActualCardType())) {
                System.out.println("Computer played: " + card.toString());
                iterator.remove();
                cardPack.add(card);

                if (card.getType().equals(CardType.K) && card.getColor().equals(CardColor.DIAMONDS)) {
                    DiamondsK();
                }
                setActualCardType(card.getType());
                setActualCardColor(card.getColor());

                hasPlayedCard = true;
                break;
            }
        }

        if (!hasPlayedCard) {
            for (Iterator<Card> iterator = computerPack.iterator(); iterator.hasNext(); ) {
                Card card = iterator.next();
                if (card.getType().equals(CardType.J)) {
                    System.out.println("Computer played: " + card.toString());
                    iterator.remove();
                    cardPack.add(card);
                    changeColor();
                    System.out.println("Computer changed color to: " + getActualCardColor());
                    hasPlayedCard = true;
                    break;
                }
            }
        }

        if (!hasPlayedCard) {
            drawCard();
        }

        setTurn(false);
        player.setTurn(true);
    }

    public void drawCard() {
        Card card = cardPack.get(0);
        computerPack.add(card);
        cardPack.remove(card);
        System.out.println("computer drew a card");
    }

    public void changeColor() {
        int heartsCount = 0;
        int diamondsCount = 0;
        int clubsCount = 0;
        int spadesCount = 0;

        for(Card card : computerPack) {
           switch (card.getColor()) {
               case HEARTS:
                   heartsCount++;
                   break;
                   case DIAMONDS:
                       diamondsCount++;
                       break;
                       case CLUBS:
                           clubsCount++;
                           break;
                           case SPADES:
                               spadesCount++;
                               break;
           }

            int max = Math.max(Math.max(heartsCount, diamondsCount), Math.max(clubsCount, spadesCount));

            CardColor newColor = null;
            if(max == heartsCount){
                newColor = CardColor.HEARTS;
            } else if (max == diamondsCount){
                newColor = CardColor.DIAMONDS;
            }else if (max == clubsCount){
                newColor = CardColor.CLUBS;
            }else if (max == spadesCount){
                newColor = CardColor.SPADES;
            }

            setActualCardColor(newColor);
            if (player != null) {
                player.setActualCardColor(newColor);
            }

        }
    }

    transient Random rd = new Random();

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        rd = new Random();
    }

    public void DiamondsK(){
        int number;
        Card card = cardPack.get(0);
            number = rd.nextInt(2);
            if (number == 1) {
                computerPack.add(card);
                cardPack.remove(card);
                System.out.println("Computer drew one extra card");
            }
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
