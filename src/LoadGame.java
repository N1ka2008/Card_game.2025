public class LoadGame extends Command{

    private SavingFiles savingFiles;

    public LoadGame(SavingFiles savingFiles) {
        this.savingFiles = savingFiles;
    }

    public boolean execute(String argument) {
        if(argument.equalsIgnoreCase("game")){
            savingFiles.load("file");
            System.out.println("Game loaded");
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
