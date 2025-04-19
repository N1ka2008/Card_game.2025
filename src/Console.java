import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Console {

    Scanner sc = new Scanner(System.in);

    private boolean exit = false;
    private HashMap<String, Command> map = new HashMap<>();

    public void inicialization(){
        map.put("draw", new DrawACard());
        map.put("play", new PlayACard());
        map.put("exit", new Exit());
    }

    private void execute(){
        System.out.println(">> ");
        String input = sc.nextLine();
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = (parts.length > 1) ? parts[1] : "";
        
        Command cmd = map.get(command);
        if (cmd != null) {
            cmd.execute(argument);
            if (cmd instanceof Exit) {
                exit = true;
            }
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

    public boolean start() throws IOException {
        inicialization();
        if(exit == false){
            execute();
            return true;
        }
        else{
            return false;
        }
    }

}
