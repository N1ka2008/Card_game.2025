package Classes;

import java.util.Random;

public class ChaosManager {

    private GameSettings settings;
    public Random rd = new Random();
    public ChaosManager(GameSettings settings) {
        this.settings = settings;
    }

    public void afterTurn(Participant player, Participant computer) {
        if (rd.nextInt(100) >= settings.getEventChance()) {
            return;
        }

        Participant onTurn = player.isTurn() ? player : computer;
        mildExtraCard(onTurn);

    }
        //mild
        public void mildExtraCard(Participant who)
        {
            int drawn = who.drawCards(1);
            if (drawn == 0) {
                return;
            }
            System.out.println("Heavy dream! " + who.getName() + " draws 1 extra card.");
        }

        public void mildReversesDirection(Participant who){

        }

}


