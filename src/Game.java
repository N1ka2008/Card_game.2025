import java.io.IOException;

public class Game {

    Cards1 cards;
    Computer computer;
    Player player;
    Pack pack;
    Console console;

    public Game() {
        pack = new Pack();
        pack.addCardsFromFile(".idea/Cards.txt");
        pack.initializeMelded();

        player = new Player(pack.cardPack, pack.getMelded());
        computer = new Computer(pack.cardPack, pack.getMelded(), player);


        pack.setPlayer(player);
        pack.setComputer(computer);

        console = new Console(pack, player, computer);

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
                pack.meldedIsA();
                if(computer.isTurn()){
                    computer.playCard();
                }else {
                    console.start();
                }
            }while(console.start());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
