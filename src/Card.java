public class Card implements Comparable<Card> {

    //chat gpt pomohl
    @Override
    public int compareTo(Card o) {
        int result = this.type.compareTo(o.type);
        if (result != 0) {
            return result;
        }
        return this.color.compareTo(o.color);
    }

    private CardType type;
    private CardColor color;
    private boolean specialCard;

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

    public String numberCards(){
        if(type == CardType.SEVEN || type == CardType.EIGHT || type == CardType.NINE || type == CardType.TEN) {
            System.out.println(type.getHodnota());
        }else {
           return type.name();
        }
        return null;
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
                "type=" + numberCards() +
                ", color=" + color +
                ", specialCard=" + specialCard +
                '}';
    }
}
