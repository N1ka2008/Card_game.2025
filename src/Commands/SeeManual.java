package Commands;
import Classes.Files;

/**
 * Class for command see manual
 */
public class SeeManual extends Command {

    private Files manual;

    public SeeManual(Files manual) {
        this.manual = manual;
    }

    /**
     * Method launches manula(), if argument is "manual"
     */
    @Override
    public boolean execute(String argument) {
        if(argument.toLowerCase().equalsIgnoreCase("manual")) {
            manual.manual(".idea/manual.txt");
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
