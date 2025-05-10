import java.io.IOException;

public class Game {

    Cards1 cards;
    Computer computer;
    Player player;
    Pack pack;
    Console console;

    public Game() {
        pack.addCardsFromFile(".idea/Cards.txt");
        pack.initializeMelded();
        pack.startCards();

        computer = new Computer(pack.cardPack, null, player);
        player = new Player(pack.cardPack, null);
        console = new Console(pack, player, computer);
        pack = new Pack(computer, player);

        player.setTurn(true);
        computer.setTurn(false);
    }

    public void play() throws IOException {
        try{
            do{
                System.out.println("Actual color: " + pack.getActualCardColor()+ "\nActual type: " + pack.getActualCardType());
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
