public class SaveGame extends Command{

    private SavingFiles savingFiles;

    public SaveGame(SavingFiles savingFiles) {
        this.savingFiles = savingFiles;
    }

    public boolean execute(String argument) {
        if(argument.equalsIgnoreCase("game")){
            savingFiles.save("file");
            System.out.println("Game saved");
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
