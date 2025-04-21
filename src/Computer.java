import java.util.TreeSet;

public class Computer extends Pack {

    public Computer(TreeSet<Card> cardPack, Card melded){
        super(cardPack, melded);
    }

    public TreeSet<Card> getComputerPack() {
        return computerPack;
    }

    public TreeSet<Card> computerPack = new TreeSet<>();
}
