public class DrawACard extends Command{

    private Pack pack;
    private Player player;
    Computer computer;

    public DrawACard(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
    }



    @Override
    public boolean execute(String argument) {
        if(argument.equalsIgnoreCase("card")){
            player.playerDrawCard();
            player.setTurn(false);
            computer.setTurn(true);
        }
        return true;
    }

    public boolean exit() {
        return false;
    }
}
