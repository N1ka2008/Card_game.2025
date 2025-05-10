public class PlayACard extends Command{

    private Player player;
    private Computer computer;

    public PlayACard(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
    }

    @Override
    public String execute(String color, String type) {
        player.playerPlayCard(color, type);
        player.setTurn(false);
        computer.setTurn(true);
        return null;
    }

    public boolean exit() {
        return false;
    }
}
