package nl.rug.ai.oop.crazyeights;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;


public class CrazyEights {
    private List<Card> deck;
    private List<Card> playerHand;
    private List<Card> computerHand;
    private Card currentCard;
    private CrazyEightsPlayer player;
    private RandomCrazyEightsPlayer computer;
    public CrazyEights() {
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();
        computerHand = new ArrayList<>();
        currentCard = null;

        // Initialize the deck
        initializeDeck();

        // Shuffle the deck
        shuffleDeck();

        // Create player and computer instances
        player = new CrazyEightsPlayer(playerHand);
        computer = new RandomCrazyEightsPlayer(computerHand);

        // Deal initial cards to the players
        dealInitialCards();

    }

    public void play() {
        System.out.println("Welcome to Crazy Eights!");
        System.out.println("The current card is: " + currentCard);

        while (!isGameOver()) {
            System.out.println();
            System.out.println("Your hand: " + playerHand);
            System.out.println("Enter the index of the card you want to play (0-" + (playerHand.size() - 1) + "): ");
            Scanner scanner = new Scanner(System.in);
            int cardIndex = scanner.nextInt();

            // Adjust the card index to match the 0-based indexing
            cardIndex--;

            // Checks if user input is valid
            if (cardIndex < 0 || cardIndex >= playerHand.size()) {
                System.out.println("Invalid card index. Try again.");
                continue;
            }

            Card selectedCard = playerHand.get(cardIndex);
            if (player.isValidMove(selectedCard, currentCard)) {
                playerHand.remove(cardIndex);
                currentCard = selectedCard;
                System.out.println("You played: " + selectedCard);
                System.out.println("The current card is now: " + currentCard);

                // Computer's turn
                computer.playTurn(currentCard);

                if (isGameOver()) {
                    break;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        System.out.println();
        if (playerHand.isEmpty()) {
            System.out.println("Congratulations! You won!");
        } else if (computerHand.isEmpty()) {
            System.out.println("Computer won! Better luck next time.");
        }
    }

    private void initializeDeck() {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Card(suit, value));
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            playerHand.add(deck.remove(0));
            computerHand.add(deck.remove(0));
        }

        // Set the current card to the top card from the deck
        currentCard = deck.remove(0);
    }

    private boolean isGameOver() {
        return playerHand.isEmpty() || computerHand.isEmpty();
    }

    public static void main(String[] args) {
        CrazyEights game = new CrazyEights();
        game.play();
    }
}
