public class SeeManual extends Command{

    private Files manual;

    public SeeManual(Files manual) {
        this.manual = manual;
    }

    @Override
    public boolean execute(String argument) {
        manual.manual(".idea/manual.txt");
        return true;
    }

    public boolean exit() {
        return false;
    }
}
