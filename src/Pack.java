import java.util.Iterator;
import java.util.TreeSet;

public class Pack {

    private Card melded;
    private Player player;
    private Computer computer;

    public TreeSet<Card> cardPack = new TreeSet<>();

    public Pack(TreeSet<Card> cardPack, Card melded) {
        this.cardPack = cardPack;
        this.melded = melded;
    }

    public Pack() {
        melded = cardPack.first();
    }

     public Pack(Computer computer, Player player) {
        this.computer = computer;
        this.player = player;
    }

    public void add(Card card) {
        if(cardPack.size() <= 32) {
            cardPack.add(card);
        } else{
            System.out.println("Cannot add card to the pack");
        }
    }

    public void startCards(){
        Iterator<Card> iterator = cardPack.iterator();
        Card first = null;
        Card second = null;
        Card third = null;
        Card fourth = null;
        Card fifth = null;
        Card sixth = null;
        Card seventh = null;
        Card eighth = null;
        for (int i = 0; i < 4 && iterator.hasNext(); i++) {
            Card current = iterator.next();
            if (i == 0) first = current;
            if (i == 1) second = current;
            if (i == 2) third = current;
            if (i == 3) fourth = current;
            if (i == 4) fifth = current;
            if (i == 5) sixth = current;
            if (i == 6) seventh = current;
            if (i == 7) eighth = current;
        }
        player.playerPack.add(first);
        player.playerPack.add(third);
        player.playerPack.add(fifth);
        player.playerPack.add(seventh);

        computer.computerPack.add(second);
        computer.computerPack.add(fourth);
        computer.computerPack.add(sixth);
        computer.computerPack.add(eighth);

        cardPack.remove(first);
        cardPack.remove(second);
        cardPack.remove(third);
        cardPack.remove(fourth);
        cardPack.remove(fifth);
        cardPack.remove(sixth);
        cardPack.remove(seventh);
        cardPack.remove(eighth);
    }
}
