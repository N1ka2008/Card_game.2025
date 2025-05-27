import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Cards1 {

    private static final Random random = new Random();

    /**
     * This method reads all cards from Cards.txt, then creates a list to store the cards and processes lines.
     * It is also important to shuffle the cards.
     * Collections.shuffle(cards) is not mine, source: Chat gpt
     * Rest of the method mine
     */
    public static List<Card> readCardsFromFile(String filePath) throws IOException {
        List<String> cardLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            cardLines.add(line.trim());
        }
        reader.close();

        List<Card> cards = new ArrayList<>();

        for (String cardLine : cardLines) {
            String[] parts = cardLine.split(";");
            if (parts.length == 2) {
                CardType type = parseCardType(parts[0]);
                CardColor color = parseCardColor(parts[1]);
                if (type != null && color != null) {
                    Card card = new Card(type, color);
                    cards.add(card);
                }
            }
        }

        Collections.shuffle(cards);

        return cards;
    }

    private static CardType parseCardType(String typeStr) {
        try {
            return CardType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid card type: " + typeStr);
            return null;
        }
    }
    
    private static CardColor parseCardColor(String colorStr) {
        try {
            return CardColor.valueOf(colorStr);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid card color: " + colorStr);
            return null;
        }
    }
}
