package Tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.List;
import Classes.Card;
import Classes.CardType;
import Classes.CardColor;
import Classes.Cards1;

/**
 * This class tests loading cards from Cards.txt.
 */
public class Cards1Test {


    private File file;

    @BeforeEach
    public void setUp() throws IOException {
        file = new File("test_cards.txt");
    }

    /**
     * Method tests loading cards from text file Cards.txt
     * cards.stream().anyMatch(c -> c.getType() and PrintWriter writer = new PrintWriter(file) is not from me. Source: jenkov.com, Claude ai
     */
    @Test
    public void testCardsFromFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("A;HEARTS");
            writer.println("K;SPADES");
            writer.println("SEVEN;DIAMONDS");
            writer.close();
        }

        List<Card> cards = Cards1.readCardsFromFile(file.getPath());

        assertEquals(3, cards.size());
        assertTrue(cards.stream().anyMatch(c -> c.getType() == CardType.A && c.getColor() == CardColor.HEARTS));
        assertTrue(cards.stream().anyMatch(c -> c.getType() == CardType.K && c.getColor() == CardColor.SPADES));
        assertTrue(cards.stream().anyMatch(c -> c.getType() == CardType.SEVEN && c.getColor() == CardColor.DIAMONDS));
    }
}
