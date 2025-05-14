import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SavingFiles {

    Computer computer;
    Player player;
    Pack pack;

    public SavingFiles(Computer computer, Player player, Pack pack) {
        this.computer = computer;
        this.player = player;
        this.pack = pack;
    }

    public void save(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }

        List<Serializable> gameState = new ArrayList<>();
        gameState.add((Serializable) computer);
        gameState.add((Serializable) player);
        gameState.add((Serializable) pack);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(gameState);
            System.out.println("Game svaed successfully");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Serializable> gameState = (List<Serializable>) ois.readObject();

            computer = (Computer) gameState.get(0);
            player = (Player) gameState.get(1);
            pack = (Pack) gameState.get(2);

            System.out.println("game loaded!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
