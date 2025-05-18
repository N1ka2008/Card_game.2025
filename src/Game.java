import java.io.IOException;

public class Game {

    Cards1 cards;
    Computer computer;
    Player player;
    Pack pack;
    Console console;
    private boolean gameIsOver = false;
    SavingFiles savingFiles;

    public Game() {
        pack = new Pack();
        pack.addCardsFromFile(".idea/Cards.txt");
        pack.initializeMelded();

        player = new Player(pack.cardPack, pack.getMelded());
        computer = new Computer(pack.cardPack, pack.getMelded(), player);

        savingFiles = new SavingFiles(computer, player, pack);

        pack.setPlayer(player);
        pack.setComputer(computer);

        console = new Console(pack, player, computer, savingFiles);

        player.setTurn(true);
        computer.setTurn(false);

        pack.startCards();
    }

    public void play() throws IOException {
        try{
            System.out.println(pack.getCardPack());
            player.getplayerPack();
            do{
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
            }while(!isGameIsOver());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }
}
