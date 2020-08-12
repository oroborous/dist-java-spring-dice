package edu.wctc.springdice.service;

import edu.wctc.springdice.entity.DiceGame;

public interface CashierService {
    boolean settleBets(int die1, int die2, DiceGame diceGame);
}
