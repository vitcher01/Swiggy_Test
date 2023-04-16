package com.vishal.bean;

import java.util.*;

public class player {
	
	//Id of the Player
    private int playerId;
    
    // cards in the hand of the player.
    private ArrayList<card> cardsInHand = new ArrayList<card>();

    public player(int playerId)
    {
        this.playerId = playerId;
    }
    
    /*
       Adds card to players Hand
     */
    public void addCard(card card) {

        cardsInHand.add(card);
    }

    /*
       Removes Card from players hand
     */
    public void removeCard(card card) {
        cardsInHand.remove(card);
        return;
    }
    
    // Get id of the player
    public int giveId() {
        return playerId;
    }
    
    // Get the cards in player's hand
    public ArrayList<card> giveCards() {
        return cardsInHand;
    }
    
    public String toString() {
        return playerId + " " + cardsInHand.toString();
    }
}
