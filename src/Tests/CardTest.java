package Tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import Classes.Card;
import Classes.CardType;
import Classes.CardColor;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing individual cards
 */
public class CardTest {

    private Card card;

    @BeforeEach
    public void setUp() {
        card = new Card(CardType.A, CardColor.HEARTS);
    }

    /**
     * method tests if card can be created
     */
    @Test
    public void testCreateCard() {
        assertEquals(CardType.A, card.getType());
        assertEquals(CardColor.HEARTS, card.getColor());
    }

}
