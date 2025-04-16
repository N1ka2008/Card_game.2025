public enum CardType {

    J, Q, K, A, SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10);


    private final int hodnota;

    CardType() {
        this.hodnota = 0;
    }

    CardType(int hodnota) {

        this.hodnota = hodnota;
    }

    public int getHodnota() {
        return hodnota;
    }
}




