public class LoadGame extends Command{

    private Files savingFiles;

    public LoadGame(Files savingFiles) {
        this.savingFiles = savingFiles;
    }

    public boolean execute(String argument) {
        if(argument.equalsIgnoreCase("game")){
            savingFiles.load("file.ser");
            System.out.println("Game loaded");
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
