public class PlayACard extends Command{

    private Player player;

    public PlayACard(Player player) {
        this.player = player;
    }

    @Override
    public String execute(String color, String type) {
        player.playerPlayCard(color, type);
        return null;
    }

    public boolean exit() {
        return false;
    }
}
