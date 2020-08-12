package edu.wctc.springdice.entity;


public class Player {
    private String name;
    private int bet;
    private int cash;


    public Player() {
        this.bet = 0;
        this.cash = 10;
    }

    public int getCash() {
        return cash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBankrupt() {
        return cash <= 0;
    }

    public void loses() {
        bet = 0;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = Math.min(cash, bet);
        cash -= bet;
    }

    public String toString() {
        return name + ": $" + cash;
    }

    public void wins() {
        cash += bet * 2;
        bet = 0;
    }
}

