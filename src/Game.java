import java.io.IOException;
import java.io.Serializable;

public class Game implements Serializable {
    
    Computer computer;
    Player player;
    Pack pack;
    Console console;
    Files files;

    public Game() {
        pack = new Pack();
        pack.addCardsFromFile(".idea/Cards.txt");

        player = new Player();
        computer = new Computer();

        pack.setPlayer(player);
        pack.setComputer(computer);

        player.cardPack = pack.cardPack;
        computer.cardPack = pack.cardPack;
        computer.player = player;

        player.setTurn(true);
        computer.setTurn(false);

        pack.startCards();

        pack.initializeMelded();

        player.setActualCardColor(pack.getActualCardColor());
        player.setActualCardType(pack.getActualCardType());
        computer.setActualCardColor(pack.getActualCardColor());
        computer.setActualCardType(pack.getActualCardType());

        if (pack.getActualCardType() == CardType.SEVEN) {
            pack.meldedIsSeven();
        }

        files = new Files(computer, player, pack);
        console = new Console(pack, player, computer, files);
    }

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

    public void play() throws IOException {
        try{
            files.manual(".idea/Manual.txt");
            do{
                pack.initializeMelded();
                System.out.println("\nRemaining in the pack: " + pack.cardPack.size() + " cards");
                System.out.println("Computer has: " + computer.getComputerPack().size() + " cards");
                player.getplayersCards();

                System.out.println("\n\nActual color: " + pack.getActualCardColor()+ "\nActual type: " + pack.getActualCardType());


                boolean skipTurn = pack.meldedIsA();
                if (skipTurn) {
                    System.out.println("An Ace was played! Turn is skipped.");
                }
                
                if(computer.isTurn()){
                    computer.playCard();
                }else {
                    console.start();
                }

                gameOver();
            }while(!console.isGameIsOver());
            System.out.println("\n\n\n🎆Game over🎆");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
