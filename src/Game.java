import java.io.IOException;

public class Game {

    Cards1 cards = new Cards1();
    Computer computer = new Computer();
    Player player = new Player();
    Pack pack = new Pack();
    Console console = new Console(pack, player);
    public void play() throws IOException {
       // cards.readCardsFromFile(".idea/Cards.txt");
        pack.addCardsFromFile(".idea/Cards.txt");
        try{
            do{
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
