public abstract class Command {

    protected String command;



    public boolean exit() {
        return false;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean execute(String argument) {
        return false;
    }

}
