public class GetPlayerPack extends Command{

    private Player player;

    public GetPlayerPack(Player player) {
        this.player = player;
    }

    public boolean execute(String argument) {
        if(argument.toLowerCase().equalsIgnoreCase("pack")) {
            player.getplayersCards();
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
