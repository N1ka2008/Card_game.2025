public class DrawACard extends Command{

    private Pack pack;
    private Player player;

    public DrawACard(Player player) {
        this.player = player;
    }



    @Override
    public boolean execute(String argument) {
        if(argument.equalsIgnoreCase("card")){
            player.playerDrawCard();
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
