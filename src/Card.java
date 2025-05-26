import java.io.Serializable;

public class Card implements Serializable {

    private CardType type;
    private CardColor color;


    public Card() {
    }

    public Card(CardType type, CardColor color) {
        this.type = type;
        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return
                "[" + color +
                " | " + type +
                "]";
    }
}
