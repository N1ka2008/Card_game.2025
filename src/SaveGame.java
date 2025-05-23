public class SaveGame extends Command{

    private Files savingFiles;
    private Console console;

    public SaveGame(Files savingFiles, Console console) {
        this.savingFiles = savingFiles;
        this.console = console;
    }

    public boolean execute(String argument) {
        if(argument.equalsIgnoreCase("game")){
            savingFiles.save("file.ser");
            console.setGameIsOver(true);
            System.out.println("Game saved");
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
