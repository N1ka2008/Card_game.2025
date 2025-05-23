import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Console {

    Scanner sc = new Scanner(System.in);
    private boolean gameIsOver = false;

    private boolean exit = false;
    private HashMap<String, Command> map = new HashMap<>();

    Pack pack;
    Player player;
    Computer computer;
    Files files;

   public Console(Pack pack, Player player, Computer computer, Files files) {
        this.pack = pack;
        this.player = player;
        this.computer = computer;
        this.files = files;
    }


    public void inicialization(){
        map.put("draw", new DrawACard(player, computer));
        map.put("play", new PlayACard(player, computer));
        map.put("exit", new Exit());
        map.put("save", new SaveGame(files, this));
        map.put("load", new LoadGame(files));
        map.put("show", new GetPlayerPack(player));
        map.put("see", new SeeManual(files));
    }

    private void execute(){
        System.out.println(">> ");
        String input = sc.nextLine();
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = (parts.length > 1) ? parts[1] : "";
        
        Command cmd = map.get(command);
        if (cmd != null) {
            cmd.execute(argument.toUpperCase());
            if (cmd instanceof Exit) {
                exit = true;
            }
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

    public boolean start() throws IOException {
        inicialization();
        if (exit == false) {
            execute();
            return true;
        } else {
            gameIsOver = true;
            return false;
        }
    }

    public boolean isGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }
}
