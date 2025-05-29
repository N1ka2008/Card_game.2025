import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class CommandTest {

    private Player player;
    private Computer computer;
    private PlayACard playCommand;
    private DrawACard drawCommand;
    private Pack pack;

    @BeforeEach
    public void setUp() {
        player = new Player();
        computer = new Computer();
        pack = new Pack();

        player.cardPack = new ArrayList<>();
        player.cardPack.add(new Card(CardType.A, CardColor.HEARTS));
        player.cardPack.add(new Card(CardType.K, CardColor.SPADES));

        player.setActualCardColor(CardColor.HEARTS);
        player.setActualCardType(CardType.A);

        playCommand = new PlayACard(player, computer);
        drawCommand = new DrawACard(player, computer, pack);
    }

    @Test
    public void testPlayACard() {
        Card testCard = new Card(CardType.A, CardColor.HEARTS);
        player.playerPack.add(testCard);

        boolean result = playCommand.execute("HEARTS A");

        assertTrue(result);
        assertFalse(player.isTurn());
        assertTrue(computer.isTurn());
    }


    @Test
    public void testDrawACard() {
        player.setTurn(true);
        computer.setTurn(false);

        boolean result = drawCommand.execute("CARD");

        assertTrue(result);
        assertFalse(player.isTurn());
        assertTrue(computer.isTurn());
    }



}
