import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Class for testing main pack
 */
public class PackTest {

    private Pack pack;
    private Player player;
    private Computer computer;

    @Before
    public void setUp() {
        pack = new Pack();
        player = new Player();
        computer = new Computer();

        pack.setPlayer(player);
        pack.setComputer(computer);

        pack.cardPack.add(new Card(CardType.A, CardColor.HEARTS));
        pack.cardPack.add(new Card(CardType.K, CardColor.SPADES));
        pack.cardPack.add(new Card(CardType.SEVEN, CardColor.DIAMONDS));
        pack.cardPack.add(new Card(CardType.EIGHT, CardColor.CLUBS));
        pack.cardPack.add(new Card(CardType.NINE, CardColor.HEARTS));
        pack.cardPack.add(new Card(CardType.TEN, CardColor.SPADES));
    }

    /**
     * Method tests initialization of melded
     */
    @Test
    public void testInitializeMelded() {
        pack.initializeMelded();

        assertNotNull(pack.getMelded());
        assertEquals(pack.getMelded().getColor(), pack.getActualCardColor());
        assertEquals(pack.getMelded().getType(), pack.getActualCardType());
    }

    /**
     * Method tests if meldedIsA works correctly
     */
    @Test
    public void testMeldedIsA() {
        pack.setActualCardType(CardType.A);
        player.setTurn(true);
        computer.setTurn(false);

        boolean result = pack.meldedIsA();

        assertTrue(result);
        assertFalse(player.isTurn());
        assertTrue(computer.isTurn());
    }

    /**
     * Method tests if meldedIsSeven works correctly
     */
    @Test
    public void testMeldedIsSeven() {
        Card sevenCard = new Card(CardType.SEVEN, CardColor.HEARTS);
        pack.setMelded(sevenCard);
        pack.setActualCardType(CardType.SEVEN);

        player.setTurn(true);
        computer.setTurn(false);

        int playerCards = player.playerPack.size();
        int cardPackSize = pack.cardPack.size();

        boolean result = pack.meldedIsSeven();

        assertTrue(result);
        assertEquals(playerCards + 2, player.playerPack.size());
        assertEquals(cardPackSize - 2, pack.cardPack.size());
        assertFalse(player.isTurn());
        assertTrue(computer.isTurn());
    }


}
