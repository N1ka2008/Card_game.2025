import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    private Card card;

    @BeforeEach
    public void setUp() {
        card = new Card(CardType.A, CardColor.HEARTS);
    }

    @Test
    public void testCreateCard() {
        assertEquals(CardType.A, card.getType());
        assertEquals(CardColor.HEARTS, card.getColor());
    }

}
