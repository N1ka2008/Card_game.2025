package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;
import Classes.Player;
import Classes.Computer;
import Classes.Pack;
import Classes.Card;
import Classes.CardType;
import Classes.CardColor;

/**
 * Class for testing Classes.Player
 */
public class PlayerTest {
    private Player player;
    private Computer computer;
    private ArrayList<Card> testCardPack;
    private Pack pack;

    @Before
    public void setUp() {
        player = new Player();
        computer = new Computer();
        pack = new Pack();

        testCardPack = new ArrayList<>();
        testCardPack.add(new Card(CardType.A, CardColor.HEARTS));
        testCardPack.add(new Card(CardType.K, CardColor.SPADES));
        testCardPack.add(new Card(CardType.SEVEN, CardColor.DIAMONDS));
        testCardPack.add(new Card(CardType.EIGHT, CardColor.CLUBS));
        testCardPack.add(new Card(CardType.NINE, CardColor.DIAMONDS));
        testCardPack.add(new Card(CardType.TEN, CardColor.HEARTS));

        pack.cardPack = new ArrayList<>(testCardPack);

        player.playerPack = new ArrayList<>();
        computer.computerPack = new ArrayList<>();

        computer.player = player;
        computer.pack = pack;
        player.computer = computer;
        player.pack = pack;
        pack.setPlayer(player);
        pack.setComputer(computer);

        pack.setActualCardColor(CardColor.HEARTS);
        pack.setActualCardType(CardType.A);

        Card initialMelded = new Card(CardType.A, CardColor.HEARTS);
        pack.setMelded(initialMelded);
    }

    /**
     * Method tests if playerDrawCard works correctly
     */
    @Test
    public void testPlayerDrawCard() {
        int packSize = pack.cardPack.size();
        int playerPackSize = player.playerPack.size();

        String result = player.playerDrawCard();

        Assert.assertEquals(packSize - 1, pack.cardPack.size());
        Assert.assertEquals(playerPackSize + 1, player.playerPack.size());
        assertTrue(result.contains("player drew a card"));
    }

    /**
     * Method tests what happens if player plays invalid card
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
