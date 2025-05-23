import java.io.*;
import java.util.ArrayList;

public class Files {

    private Computer computer;
    private Player player;
    private Pack pack;

    public Files(Computer computer, Player player, Pack pack) {
        this.computer = computer;
        this.player = player;
        this.pack = pack;
    }

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
                System.out.println("Game saved successfully!");
            }
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    public void load(String fileName) {
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                ArrayList<Object> gameState = (ArrayList<Object>) ois.readObject();

                this.computer = (Computer) gameState.get(0);
                this.player = (Player) gameState.get(1);
                this.pack = (Pack) gameState.get(2);

                pack.setPlayer(player);
                pack.setComputer(computer);
                computer.player = player;

                System.out.println("Game loaded successfully!");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Save file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found when loading game: " + e.getMessage());
        } catch (ClassCastException e) {
            System.err.println("Error casting loaded objects: " + e.getMessage());
        }
    }

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
