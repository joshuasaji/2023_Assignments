package nl.rug.ai.oop.crazyeights;
import java.util.List;
import java.util.Random;

public class RandomCrazyEightsPlayer {
    private List<Card> hand;

    public RandomCrazyEightsPlayer(List<Card> hand) {
        this.hand = hand;
    }

    public void playTurn(Card currentCard) {
        System.out.println("Computer's turn:");

        // Find a valid card to play
        Card selectedCard = findValidCard(currentCard);

        if (selectedCard != null) {
            hand.remove(selectedCard);
            System.out.println("Computer played: " + selectedCard);
        } else {
            // Draw a card from the deck
            Card drawnCard = drawCard();
            System.out.println("Computer drew a card: " + drawnCard);

            // Check if the drawn card is a valid move
            if (isValidMove(drawnCard, currentCard)) {
                hand.remove(drawnCard);
                System.out.println("Computer played: " + drawnCard);
            } else {
                System.out.println("Computer cannot play. Turn skipped.");
            }
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
        // Simulate drawing a card from the deck
        // For simplicity, let's assume we generate a random card

        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

        Random random = new Random();
        String suit = suits[random.nextInt(suits.length)];
        String value = values[random.nextInt(values.length)];

        return new Card(suit, value);
    }

    private boolean isValidMove(Card card, Card currentCard) {
        return card.getSuit().equals(currentCard.getSuit()) || card.getValue().equals(currentCard.getValue()) || card.getValue().equals("8");
    }
}
