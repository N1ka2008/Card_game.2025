package Classes;

import java.io.Serializable;

public class GameSettings implements Serializable {

    private int eventChance;   // šance události po tahu v %, Classic = 0, Chaos = 40
    private int mildWeight;    // 60
    private int mediumWeight;  // 25
    private int extremeWeight; //15

    public GameSettings(int eventChance, int mildWeight, int mediumWeight, int extremeWeight) {
        this.eventChance = eventChance;
        this.mildWeight = mildWeight;
        this.mediumWeight = mediumWeight;
        this.extremeWeight = extremeWeight;
    }

    public int getEventChance() {
        return eventChance;
    }

    public void setEventChance(int eventChance) {
        this.eventChance = eventChance;
    }

    public int getMildWeight() {
        return mildWeight;
    }

    public void setMildWeight(int mildWeight) {
        this.mildWeight = mildWeight;
    }

    public int getMediumWeight() {
        return mediumWeight;
    }

    public void setMediumWeight(int mediumWeight) {
        this.mediumWeight = mediumWeight;
    }

    public int getExtremeWeight() {
        return extremeWeight;
    }

    public void setExtremeWeight(int extremeWeight) {
        this.extremeWeight = extremeWeight;
    }
}
