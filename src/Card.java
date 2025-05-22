import java.io.Serializable;

public class Card implements Serializable {

    private CardType type;
    private CardColor color;
    private boolean specialCard;


    public Card() {
    }

    public Card(CardType type, CardColor color, boolean specialCard) {
        this.type = type;
        this.color = color;
        this.specialCard = specialCard;
    }

    public boolean isSpecial() {
        specialCard = false;
        if(type == CardType.J || type == CardType.SEVEN || type == CardType.K || type == CardType.A) {
            specialCard = true;
        }
        return specialCard;
    }

    public boolean isSpecialCard() {
        return specialCard;
    }

    public void setSpecialCard(boolean specialCard) {
        this.specialCard = specialCard;
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
        return "Card{" +
                "type=" + type +
                ", color=" + color +
                '}';
    }
}
