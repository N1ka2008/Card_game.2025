import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Class for testing Player
 */
public class PlayerTest {
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
     * Method tests if playerDrawCard works correctly
     */
    @Test
    public void testPlayerDrawCard() {
        int initialPackSize = player.cardPack.size();
        int initialPlayerPackSize = player.playerPack.size();

        String result = player.playerDrawCard();

        assertEquals(initialPackSize - 1, player.cardPack.size());
        assertEquals(initialPlayerPackSize + 1, player.playerPack.size());
        assertTrue(result.contains("player drew a card"));
    }

    /**
     * Method tests what happens if player plays invalid  card
     */
    @Test
    public void testPlayerPlayInvalidCard() {
        Card invalidCard = new Card(CardType.EIGHT, CardColor.CLUBS);
        player.playerPack.add(invalidCard);

        String result = player.playerPlayCard("CLUBS", "EIGHT");

        assertEquals("You cannot play this card", result);
        assertTrue(player.playerPack.contains(invalidCard));
    }
}
