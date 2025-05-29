import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Class for testinf Computer
 */
public class ComputerTest {

    private Player player;
    private Computer computer;
    private ArrayList<Card> testCardPack;

    @Before
    public void setUp() {
        testCardPack = new ArrayList<>();
        testCardPack.add(new Card(CardType.A, CardColor.HEARTS));
        testCardPack.add(new Card(CardType.K, CardColor.SPADES));
        testCardPack.add(new Card(CardType.SEVEN, CardColor.DIAMONDS));
        testCardPack.add(new Card(CardType.EIGHT, CardColor.CLUBS));
        testCardPack.add(new Card(CardType.NINE, CardColor.DIAMONDS));
        testCardPack.add(new Card(CardType.TEN, CardColor.HEARTS));

        player = new Player();
        computer = new Computer();

        player.cardPack = new ArrayList<>(testCardPack);

        player.playerPack = new ArrayList<>();

        computer.computerPack = new ArrayList<>();
        computer.cardPack = player.cardPack;

        player.setPlayer(player);
        player.setComputer(computer);

        computer.player = player;

        player.setActualCardColor(CardColor.HEARTS);
        player.setActualCardType(CardType.A);

        Card initialMelded = new Card(CardType.A, CardColor.HEARTS);
        player.setMelded(initialMelded);
    }

    /**
     * Method tests if Computer can draw a card
     */
    @Test
    public void testDrawCard() {
        int initialComputerPackSize = computer.computerPack.size();
        int initialCardPackSize = computer.cardPack.size();

        computer.drawCard();

        assertEquals(initialComputerPackSize + 1, computer.computerPack.size());
        assertEquals(initialCardPackSize - 1, computer.cardPack.size());
    }

}
