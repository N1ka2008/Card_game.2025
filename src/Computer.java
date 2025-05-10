import java.util.Iterator;
import java.util.TreeSet;

public class Computer extends Pack {

    private boolean turn;

    public Computer() {
    }

    public Computer(TreeSet<Card> cardPack, Card melded){
        super(cardPack, melded);
    }

    public TreeSet<Card> getComputerPack() {
        return computerPack;
    }

    public TreeSet<Card> computerPack = new TreeSet<>();

    public void playCard() {
        for(Card card : computerPack) {
            if (card.getColor().equals(getActualCardColor()) || card.getType().equals(getActualCardType())) {
                computerPack.remove(card);
                cardPack.add(card);
                System.out.println("Computer played card");
            }else if(card.getType().equals(CardType.J)) {
                changeColor();
                computerPack.remove(card);
                cardPack.add(card);
                System.out.println("Computer changed color");
            } else {
                drawCard();
                mendedIsSeven();
            }
        }
    }

    public void drawCard() {
        Card card = cardPack.first();
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

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
