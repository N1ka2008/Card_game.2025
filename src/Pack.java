import java.util.TreeSet;

public class Pack {

    public TreeSet<Card> cardPack = new TreeSet<>();

    public void add(Card card) {
        if(cardPack.size() <= 32) {
            cardPack.add(card);
        } else{
            System.out.println("Cannot add card to the pack");
        }
    }
}
