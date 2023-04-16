package com.vishal.bean;

import java.util.*;

public class Player {
	
	//Id of the Player
    private int playerId;
    
    // cards in the hand of the player.
    private ArrayList<Card> cardsInHand = new ArrayList<Card>();

    public Player(int playerId)
    {
        this.playerId = playerId;
    }
    
    /*
       Adds card to players Hand
     */
    public void addCard(Card card) {

        cardsInHand.add(card);
    }

    /*
       Removes Card from players hand
     */
    public void removeCard(Card card) {
        cardsInHand.remove(card);
        return;
    }
    
    // Get id of the player
    public int giveId() {
        return playerId;
    }
    
    // Get the cards in player's hand
    public ArrayList<Card> giveCards() {
        return cardsInHand;
    }
    
    public String toString() {
        return playerId + " " + cardsInHand.toString();
    }
}
