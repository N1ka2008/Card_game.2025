package Classes;

import java.io.*;
import java.util.ArrayList;

/**
 * Class for files
 */
public class Files {

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
        try {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }

            ArrayList<Object> gameState = new ArrayList<>();
            gameState.add(computer);
            gameState.add(player);
            gameState.add(pack);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(gameState);
                System.out.println("Classes.Game saved successfully!");
            }
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    /**
     * Method copies data from a saved file to the current object
     */
    public boolean load(String fileName) {
        try {
            try {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                    ArrayList<Object> gameState = (ArrayList<Object>) ois.readObject();

                    Computer loadedComputer = (Computer) gameState.get(0);
                    Player loadedPlayer = (Player) gameState.get(1);
                    Pack loadedPack = (Pack) gameState.get(2);

                    this.computer.computerPack = loadedComputer.computerPack;
                    this.computer.setTurn(loadedComputer.isTurn());

                    this.player.playerPack = loadedPlayer.playerPack;
                    this.player.setTurn(loadedPlayer.isTurn());

                    this.pack.cardPack = loadedPack.cardPack;
                    this.pack.setMelded(loadedPack.getMelded());
                    this.pack.setActualCardColor(loadedPack.getActualCardColor());
                    this.pack.setActualCardType(loadedPack.getActualCardType());

                    this.pack.setPlayer(this.player);
                    this.pack.setComputer(this.computer);
                    this.computer.player = this.player;
                    this.player.computer = this.computer;
                    this.player.pack = this.pack;
                    this.computer.pack = this.pack;

                    System.out.println("Classes.Game loaded successfully!");
                    return true;
                }
            } catch (FileNotFoundException e) {
                System.err.println("Saved file not found: " + e.getMessage());
                return false;
            } catch (IOException e) {
                System.err.println("Error loading game: " + e.getMessage());
                return false;
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found when loading game: " + e.getMessage());
                return false;
            } catch (ClassCastException e) {
                System.err.println("Error casting loaded objects: " + e.getMessage());
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
