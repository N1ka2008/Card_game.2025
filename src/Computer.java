import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Computer extends Pack {

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

    public void playCard() {
        System.out.println("\nComputers turn");

        boolean hasPlayedCard = false;

        for (Iterator<Card> iterator = computerPack.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getColor().equals(getActualCardColor()) || card.getType().equals(getActualCardType())) {
                System.out.println("Computer played: [" + card.getColor() + " | " + card.getType() + "]");
                iterator.remove();
                cardPack.add(card);
                setActualCardColor(card.getColor());
                setActualCardType(card.getType());
                if(card.getType().equals(CardType.K) && card.getColor().equals(CardColor.DIAMONDS)) {
                    DiamondsK();
                }
                    hasPlayedCard = true;

                break;
            }
        }

        if (!hasPlayedCard) {
            for (Iterator<Card> iterator = computerPack.iterator(); iterator.hasNext(); ) {
                Card card = iterator.next();
                if (card.getType().equals(CardType.J)) {
                    System.out.println("Computer played: [" + card.getColor() + " | " + card.getType() + "]");
                    iterator.remove();
                    cardPack.add(card);
                    setActualCardType(card.getType());
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
        meldedIsSeven();
        setTurn(false);
        player.setTurn(true);
    }

    public void drawCard() {
        Card card = cardPack.get(0);
        computerPack.add(card);
       cardPack.remove(card);
        System.out.println("computer drawed a card");
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

            if(max == heartsCount){
                setActualCardColor(CardColor.HEARTS);
            } else if (max == diamondsCount){
                setActualCardColor(CardColor.DIAMONDS);
            }else if (max == clubsCount){
                setActualCardColor(CardColor.CLUBS);
            }else if (max == spadesCount){
                setActualCardColor(CardColor.SPADES);
            }

        }
    }

    Random rd = new Random();

    public void DiamondsK(){
        int number;
        Card card = cardPack.get(0);
            number = rd.nextInt(1);
            if (number == 1) {
                computerPack.add(card);
                cardPack.remove(card);
                System.out.println("Computer drawed one extra card");
            }
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
