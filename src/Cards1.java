import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Cards1 {

    private static final Random random = new Random();

    public static List<Card> readCardsFromFile(String filePath) throws IOException {
        // Read all cards into a list first
        List<String> cardLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            cardLines.add(line.trim());
        }
        reader.close();

        // Create list to store cards
        List<Card> cards = new ArrayList<>();

        // Process lines
        for (String cardLine : cardLines) {
            String[] parts = cardLine.split(";");
            if (parts.length == 2) {
                CardType type = parseCardType(parts[0]);
                CardColor color = parseCardColor(parts[1]);
                if (type != null && color != null) {
                    boolean isSpecial = (type == CardType.J || type == CardType.SEVEN || type == CardType.K || type == CardType.A);
                    Card card = new Card(type, color, isSpecial);
                    cards.add(card);
                }
            }
        }

        // Shuffle the cards
        Collections.shuffle(cards);

        return cards;
    }

    //chatgpt helped
    private static CardType parseCardType(String typeStr) {
        try {
            return CardType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid card type: " + typeStr);
            return null;
        }
    }

    //chatgpt helped
    private static CardColor parseCardColor(String colorStr) {
        try {
            return CardColor.valueOf(colorStr);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid card color: " + colorStr);
            return null;
        }
    }
}
