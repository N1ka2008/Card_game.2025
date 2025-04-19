public class Exit extends Command{
    @Override
    public boolean execute(String argument) {
        return false;
    }

    public boolean exit() {
        return true;
    }
}
