package nl.rug.ai.oop.crazyeights;

/**
 * Represents a playing card
 */
public class Card {
    private final String suit;
    private final String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }
    @Override
    public String toString() {
        return suit + " " + value;
    }
}
