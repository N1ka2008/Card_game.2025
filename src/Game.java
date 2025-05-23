import java.io.IOException;
import java.io.Serializable;

public class Game implements Serializable {
    
    Computer computer;
    Player player;
    Pack pack;
    Console console;
    SavingFiles savingFiles;

    public Game() {
        pack = new Pack();
        pack.addCardsFromFile(".idea/Cards.txt");

        player = new Player();
        computer = new Computer();

        pack.setPlayer(player);
        pack.setComputer(computer);

        pack.initializeMelded();

        player.cardPack = pack.cardPack;
        player.setActualCardColor(pack.getActualCardColor());
        player.setActualCardType(pack.getActualCardType());

        computer.cardPack = pack.cardPack;
        computer.setActualCardColor(pack.getActualCardColor());
        computer.setActualCardType(pack.getActualCardType());
        computer.player = player;

        savingFiles = new SavingFiles(computer, player, pack);
        console = new Console(pack, player, computer, savingFiles);

        player.setTurn(true);
        computer.setTurn(false);

        pack.startCards();

        if (pack.getActualCardType() == CardType.SEVEN) {
            pack.meldedIsSeven();

        }
    }

    public String gameOver(){
        if(player.getPlayerPack().isEmpty()){
            console.setGameIsOver(true);
            return "Player is the winner";
        }else if(computer.getComputerPack().isEmpty()){
            console.setGameIsOver(true);
            return "Computer is the winner";
        }
        return "";
    }

    public void play() throws IOException {
        try{
            System.out.println(pack.getCardPack());
            player.getplayersCards();
            do{
                pack.initializeMelded();
                System.out.println("Actual color: " + pack.getActualCardColor()+ "\nActual type: " + pack.getActualCardType());

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
