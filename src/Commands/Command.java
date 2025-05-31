package Commands;

/**
 * Abstract class for commands
 */
public abstract class Command {

    protected String command;

    /**
     * Method exit
     */
    public boolean exit() {
        return false;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Method execute
     */
    public boolean execute(String argument) {
        return false;
    }

}
