package com.vishal.bean;

import com.vishal.constants.Suits;

public class Card {

    private int number;
    private Suits suit;
    

    public Card(int num, Suits s) {
        number = num;
        suit = s;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }
    public Suits getSuit() {
        return suit;
    }

    public String toString() {
        return number + " " + suit;
    }
}
