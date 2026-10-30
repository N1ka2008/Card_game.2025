package Classes;

import java.io.*;
import java.util.ArrayList;

/**
 * Class for files
 */
public class Files {

    public static final String CARDS_FILE = ".idea/Cards.txt";
    public static final String MANUAL_FILE = ".idea/Manual.txt";
    public static final String SAVE_FILE = "file.ser";

    private Computer computer;
    private Player player;
    private Pack pack;

    public Files(Computer computer, Player player, Pack pack) {
        this.computer = computer;
        this.player = player;
        this.pack = pack;
    }

    /**
     * Method saves the values to a file
     */
    public void save(String fileName) {
        ArrayList<Object> gameState = new ArrayList<>();
        gameState.add(computer);
        gameState.add(player);
        gameState.add(pack);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(gameState);
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    /**
     * Method copies data from a saved file to the current objects
     * The links between the objects (pack, opponent) stay the same, only hands, piles, turns and colors are copied
     */
    public boolean load(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<Object> gameState = (ArrayList<Object>) ois.readObject();

            Computer loadedComputer = (Computer) gameState.get(0);
            Player loadedPlayer = (Player) gameState.get(1);
            Pack loadedPack = (Pack) gameState.get(2);

            computer.setHand(loadedComputer.getHand());
            computer.setTurn(loadedComputer.isTurn());

            player.setHand(loadedPlayer.getHand());
            player.setTurn(loadedPlayer.isTurn());

            pack.setCardPack(loadedPack.getCardPack());
            pack.setMelded(loadedPack.getMelded());
            pack.setActualCardColor(loadedPack.getActualCardColor());
            pack.setActualCardType(loadedPack.getActualCardType());

            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Saved file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found when loading game: " + e.getMessage());
        } catch (ClassCastException e) {
            System.err.println("Error casting loaded objects: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method loads manual from text file Manual.txt
     */
    public void manual(String text) {
        try (BufferedReader reader = new BufferedReader(new FileReader(text))) {
            String radek;
            while ((radek = reader.readLine()) != null) {
                System.out.println(radek);
            }
        } catch (IOException e) {
            System.out.println("Manual not found: " + text);
        }
    }
}
