import java.io.IOException;
import java.io.Serializable;

/**
 * Class for game logic
 */
public class Game implements Serializable {
    
    Computer computer;
    Player player;
    Pack pack;
    Console console;
    Files files;

    /**
     * This constructor initializes all objects important for the game
     */
    public Game() {
        pack = new Pack();
        pack.addCardsFromFile(".idea/Cards.txt");

        player = new Player();
        computer = new Computer();

        pack.setPlayer(player);
        pack.setComputer(computer);
        pack.setSpecialEfect(false);

        computer.player = player;
        computer.pack = pack;
        player.computer = computer;
        player.pack = pack;

        player.setTurn(true);
        computer.setTurn(false);

        pack.startCards();
        pack.initializeMelded();

        if (pack.getActualCardType() == CardType.SEVEN) {
            pack.meldedIsSeven();
        }

        files = new Files(computer, player, pack);
        console = new Console(pack, player, computer, files);
    }

    /**
     * Method controls whether Player or Computer have empty pack
     * It sets gameIsOver to true
     */
    public String gameOver(){
        if(player.getPlayerPack().isEmpty()){
            console.setGameIsOver(true);
            return "\n\n🎉Player is the winner🎂";
        }else if(computer.getComputerPack().isEmpty()){
            console.setGameIsOver(true);
            return "\n\n✨Computer is the winner✨";
        }
        return "";
    }

    /**
     * Method contains game logic
     * In each round it initializes melded and synchronizes actual color and type
     * It controls who can play
     */
    public void play() throws IOException {
        try{
            files.manual(".idea/Manual.txt");
            System.out.println("\n\nActual color: " + pack.getActualCardColor() + "\nActual type: " + pack.getActualCardType());

            do{
                pack.initializeMelded();

                System.out.println("\nRemaining in the pack: " + pack.cardPack.size() + " cards");
                System.out.println("Computer has: " + computer.getComputerPack().size() + " cards");
                player.getplayersCards();

                if(!pack.isSpecialEfect()) {
                    pack.meldedIsA();
                    pack.meldedIsSeven();
                    pack.setSpecialEfect(true);
                }

                if (computer.isTurn()) {
                    computer.playCard();
                } else {
                    console.start();
                }

                System.out.println("\n\nActual color: " + pack.getActualCardColor() + "\nActual type: " + pack.getActualCardType());

                System.out.println(gameOver());
            }while(!console.isGameIsOver());
            System.out.println("\n\n\n🎆Game over🎆");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
