import java.util.TreeSet;

public class Computer extends Pack {

    public Computer(TreeSet<Card> cardPack, Card melded){
        super(cardPack, melded);
    }

    public TreeSet<Card> getComputerPack() {
        return computerPack;
    }

    public TreeSet<Card> computerPack = new TreeSet<>();

    public void playCard() {
        for(Card card : computerPack) {
            if (card.getColor().equals(getMelded().getColor()) || card.getType().equals(getMelded().getType())) {
                computerPack.remove(card);
                cardPack.add(card);
                System.out.println("Computer played card");
            }else{
                drawCard();
            }
        }
    }

    public void drawCard() {
        Card card = cardPack.first();
        computerPack.add(card);
       cardPack.remove(card);
        System.out.println("computer drawed a card");
    }
}
