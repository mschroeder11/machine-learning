package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;

/**
 * Represents a playing card found in a 52 card deck A card will have a suit of: Clubs, Spades,
 * Hearts, Diamonds And a rank of: Ace, One-Ten, Jack, Queen, King.
 */
public class Card {
  
  /**
   * represents the ranks of a card.
   */
  public enum Rank {
    /**
     * represents the possible ranks.
     */
    ACE(1, "A"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"),
    SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"), JACK(11, "J"),
    QUEEN(12, "Q"), KING(13, "K");

    private int value;
    private String symbol;

    /**
     * Constructor for Rank enum.
     * @param value  the numeric value of the rank
     * @param symbol the symbolic value of the rank
     */
    Rank(int value, String symbol) {
      this.value = value;
      this.symbol = symbol;
    }

    @Override
    public String toString() {
      return this.symbol;
    }

    /**
     * Returns the numeric value of the Rank.
     *
     * @return the numeric value of the Rank.
     */
    public int value() {
      return this.value;
    }
  }
  
  /**
   * Represents the suits of a card.
   */
  public enum Suit {
    /**
     * represents the possible suits.
     */
    CLUB('♣'), DIAMOND('♦'), HEART('♥'), SPADE('♠');

    private char symbol;

    /**
     * Constructor for Rank enum.
     * @param value  the numeric value of the rank
     * @param symbol the symbolic value of the rank
     */
    Suit(char symbol) {
      this.symbol = symbol;
    }

    @Override
    public String toString() {
      return this.symbol + "";
    }
  }

  private final Suit suit;
  private final Rank rank;

  
  /**
   * constructs an {@code Card} object.
   * 
   * @param suit the suit of the card.
   * @param rank the rank of the card.
   * @throws IllegalArgumentException if the suit or rank is invalid
   */
  public Card(Suit suit, Rank rank) {
    this.suit = suit;
    this.rank = rank;
  }
  
  @Override
  public String toString() {
    return rank.toString() + suit.toString();
  }
  
 
  @Override
  public int hashCode() {
    return Objects.hash(rank, suit);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Card)) {
      return false;
    }
    Card other = (Card) obj;
    return rank == other.rank && suit == other.suit;
  }

  /**
   * gets the rank of this card.
   * 
   * @return this.rank, which corresponds to the value of the card as well.
   */
  public int getRank() {
    return rank.value();
  }

  /**
   * Produces the sum of the scores of two cards.
   * 
   * @param other other card to be summed with this one.
   * @return the sum of this card and a second card's scores.
   */
  public int sum(Card other) {
    return this.rank.value + other.getRank();
  }


}
