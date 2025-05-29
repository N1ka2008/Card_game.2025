/**
 * Class for command exit
 */
public class Exit extends Command{

    /**
     * Method execute
     */
    @Override
    public boolean execute(String argument) {
        return false;
    }

    /**
     * method exit
     */
    public boolean exit() {
        return true;
    }
}
