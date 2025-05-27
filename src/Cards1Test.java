import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.Path;
import java.util.List;

/**
 * This class tests coading cards from Cards.txt.
 * Class is not mine, source: Claude ai
 */
public class Cards1Test {

    @TempDir
    Path tempDir;

    private File file;

    @BeforeEach
    public void setUp() throws IOException {
        file = tempDir.resolve("test_cards.txt").toFile();
    }

    @Test
    public void testCardsFromFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("A;HEARTS");
            writer.println("K;SPADES");
            writer.println("SEVEN;DIAMONDS");
        }

        List<Card> cards = Cards1.readCardsFromFile(file.getAbsolutePath());

        assertEquals(3, cards.size());
        assertTrue(cards.stream().anyMatch(c -> c.getType() == CardType.A && c.getColor() == CardColor.HEARTS));
        assertTrue(cards.stream().anyMatch(c -> c.getType() == CardType.K && c.getColor() == CardColor.SPADES));
        assertTrue(cards.stream().anyMatch(c -> c.getType() == CardType.SEVEN && c.getColor() == CardColor.DIAMONDS));
    }
}
