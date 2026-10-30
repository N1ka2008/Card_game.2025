package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Class for reading and loading cards from text file Cards.txt
 */
public class Cards1 {

    /**
     * This method reads all cards from Cards.txt (one card per line, format TYPE;COLOR), creates a list
     * to store the cards and shuffles it.
     * Collections.shuffle(cards) is not mine, source: Chat gpt
     */
    public static List<Card> readCardsFromFile(String filePath) throws IOException {
        List<Card> cards = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    CardType type = parseCardType(parts[0]);
                    CardColor color = parseCardColor(parts[1]);
                    if (type != null && color != null) {
                        cards.add(new Card(type, color));
                    }
                }
            }
        }

        Collections.shuffle(cards);

        return cards;
    }

    /**
     * Method parses string to Enum value of Classes.CardType
     */
    private static CardType parseCardType(String typeStr) {
        try {
            return CardType.parse(typeStr);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid card type: " + typeStr);
            return null;
        }
    }

    /**
     * Method parses string to Enum value of Classes.CardColor
     */
    private static CardColor parseCardColor(String colorStr) {
        try {
            return CardColor.valueOf(colorStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid card color: " + colorStr);
            return null;
        }
    }
}
