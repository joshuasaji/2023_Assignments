package nl.rug.ai.oop.crazyeights;
import java.util.List;
import java.util.Random;

public class CrazyEightsPlayer {
    private List<Card> hand;

    public CrazyEightsPlayer(List<Card> hand) {
        this.hand = hand;
    }

    public void playTurn(Card currentCard) {
        System.out.println("Player's turn:");

        // Check if the player has a valid card to play
        Card selectedCard = findValidCard(currentCard);

        if (selectedCard != null) {
            // Remove the card from the hand and play it
            hand.remove(selectedCard);
            System.out.println("Player played: " + selectedCard);
        } else {
            // Player cannot play a card, so they draw a card
            Card drawnCard = drawCard();
            hand.add(drawnCard);
            System.out.println("Player drew a card: " + drawnCard);
        }
    }

    private Card findValidCard(Card currentCard) {
        for (Card card : hand) {
            if (isValidMove(card, currentCard)) {
                return card;
            }
        }
        return null;
    }

    private Card drawCard() {
        // Generate a random card

        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

        Random random = new Random();
        String suit = suits[random.nextInt(suits.length)];
        String value = values[random.nextInt(values.length)];

        return new Card(suit, value);
    }

    public boolean isValidMove(Card card, Card currentCard) {
        return card.getSuit().equals(currentCard.getSuit()) || card.getValue().equals(currentCard.getValue()) || card.getValue().equals("8");
    }
}

