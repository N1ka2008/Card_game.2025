public class Exit extends Command{

    @Override
    public boolean execute(String argument) {

        return false;
    }

    public boolean exit() {
        System.out.println("Game over!");
        return true;
    }
}
