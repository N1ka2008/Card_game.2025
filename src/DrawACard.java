/**
 * Class for draw card command
 */
public class DrawACard extends Command{

    private Pack pack;
    private Player player;
    Computer computer;

    public DrawACard(Player player, Computer computer, Pack pack) {
        this.player = player;
        this.computer = computer;
        this.pack = pack;
    }

    /**
     * Method launches playerDrawCard method, if argument is "card"
     */
    @Override
    public boolean execute(String argument) {
        if(argument.toLowerCase().equalsIgnoreCase("card")){
            System.out.println(player.playerDrawCard());
            player.setTurn(false);
            computer.setTurn(true);
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
