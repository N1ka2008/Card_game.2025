package Classes;

/**
 * Enum for card type
 * Number cards ONE - SIX have no effect, the rest are effect cards:
 * AWAKENING = Probuzení, NIGHTMARE = Noční můra, DEJAVU = Déjà vu, PARALYSIS = Paralýza, TWIST = Zvrat
 */
public enum CardType {

    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    AWAKENING, NIGHTMARE, DEJAVU, PARALYSIS, TWIST;

    private final int value;

    CardType() {
        this(0);
    }

    CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Number cards have a value bigger than 0, effect cards have 0
     */
    public boolean isNumber() {
        return value > 0;
    }

    /**
     * Method parses text to CardType. It accepts names (THREE) and also digits (3)
     */
    public static CardType parse(String text) {
        String t = text.trim().toUpperCase();
        for (CardType type : values()) {
            if (type.name().equals(t) || (type.isNumber() && String.valueOf(type.value).equals(t))) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown card type: " + text);
    }

    @Override
    public String toString() {
        return isNumber() ? String.valueOf(value) : name();
    }
}
