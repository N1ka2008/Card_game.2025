package Commands;
import Classes.Files;

/**
 * Class for command load game
 */
public class LoadGame extends Command {

    private Files savingFiles;

    public LoadGame(Files savingFiles) {
        this.savingFiles = savingFiles;
    }

    /**
     * Method launches load(), if argument is "game"
     */
    public boolean execute(String argument) {
        if(argument.toLowerCase().equalsIgnoreCase("game")){
            boolean success = savingFiles.load("file.ser");
            if (success) {
                System.out.println("Classes.Game loaded successfully!");
            } else {
                System.out.println("Failed to load game");
            }
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
