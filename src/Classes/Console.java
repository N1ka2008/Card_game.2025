
package Classes;

import Commands.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Classes.Console for commands
 */
public class Console {

    /**
     * The only Scanner of the whole game (Player uses it too), two Scanners on System.in can steal input from each other
     */
    public static final Scanner scanner = new Scanner(System.in);

    private boolean gameIsOver = false;

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
        inicialization();
    }

    /**
     * Method initializes the commands (it runs once, in the constructor)
     */
    private void inicialization() {
        map.put("draw", new DrawACard(player, computer, pack));
        map.put("play", new PlayACard(player, computer, pack));
        map.put("exit", new Exit());
        map.put("save", new SaveGame(files, this));
        map.put("load", new LoadGame(files));
        map.put("show", new GetPlayerPack(player));
        map.put("see", new SeeManual(files));
    }

    /**
     * Method executes the command which player types
     */
    private void execute() {
        System.out.print(">> ");
        if (!scanner.hasNextLine()) {
            gameIsOver = true;
            return;
        }
        String input = scanner.nextLine();
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1].toUpperCase() : "";

        Command cmd = map.get(command);
        if (cmd != null) {
            cmd.execute(argument);
            if (cmd instanceof Exit) {
                gameIsOver = true;
            }
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

    /**
     * Method reads and executes one command. It returns false, if the game is over
     */
    public boolean start() {
        if (gameIsOver) {
            return false;
        }
        execute();
        return !gameIsOver;
    }

    public boolean isGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }
}