package Classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Class for computer and its methods, extends from Classes.Pack
 */
public class Computer implements Serializable {

    private boolean turn;
    public Player player;
    public Pack pack;

    public Computer() {
    }

    public Computer(Player player, Pack pack) {
        this.player = player;
        this.pack = pack;
    }

    public ArrayList<Card> getComputerPack() {
        return computerPack;
    }

    public ArrayList<Card> computerPack = new ArrayList<>();

    /**
     * This method controls which cards can computer play and then plays them.
     * It also applies methods DiamondsK(), changeColor() and drawCard()
     * Iterator<Classes.Card>, iterator.hasNext() and iterator.next() is not mine. Source: Chat gpt
     * Rest of the method is mine
     */
    public void playCard() {
        System.out.println("\nComputer's turn");
        boolean hasPlayedCard = false;

        for (Iterator<Card> iterator = computerPack.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getColor().equals(pack.getActualCardColor()) || card.getType().equals(pack.getActualCardType())) {
                System.out.println("Computer played: " + card.toString());
                iterator.remove();
                pack.cardPack.add(card);
                pack.setSpecialEfect(false);

                if (card.getType().equals(CardType.K) && card.getColor().equals(CardColor.DIAMONDS)) {
                    DiamondsK();
                }
                pack.setActualCardType(card.getType());
                pack.setActualCardColor(card.getColor());

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
                    pack.cardPack.add(card);
                    pack.setSpecialEfect(false);
                    changeColor();
                    pack.setActualCardType(CardType.J);
                    System.out.println("Computer changed color to: " + pack.getActualCardColor());
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

    /**
     * This method allows Classes.Computer to draw a card
     */
    public void drawCard() {
        Card card = pack.cardPack.get(0);
        computerPack.add(card);
        pack.cardPack.remove(card);
        System.out.println("computer drew a card");
    }

    /**
     * This method allows Classes.Computer to change the actual card color
     * first it counts which color is the most and then changes the actual color to it
     */
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

            pack.setActualCardColor(newColor);


        }
    }

    transient Random rd = new Random();

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        rd = new Random();
    }

    /**
     * Method for Diamonds K
     * if Random is 1, Computer will draw one extra card
     */
    public void DiamondsK(){
        int number;
        Card card = pack.cardPack.get(0);
            number = rd.nextInt(2);
            if (number == 1) {
                computerPack.add(card);
                pack.cardPack.remove(card);
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
