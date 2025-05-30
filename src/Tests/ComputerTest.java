package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import Classes.Player;
import Classes.Pack;
import Classes.Card;
import Classes.Computer;
import Classes.CardType;
import Classes.CardColor;

/**
 * Class for testinf Classes.Computer
 */
public class ComputerTest {

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
     * Method tests if Classes.Computer can draw a card
     */
    @Test
    public void testDrawCard() {
        int computerPackSize = computer.computerPack.size();
        int cardPackSize = pack.cardPack.size();

        computer.drawCard();

        Assert.assertEquals(computerPackSize + 1, computer.computerPack.size());
        Assert.assertEquals(cardPackSize - 1, pack.cardPack.size());
    }

}
