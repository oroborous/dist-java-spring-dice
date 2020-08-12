package edu.wctc.springdice.service;

import edu.wctc.springdice.entity.DiceGame;
import edu.wctc.springdice.entity.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenSumWinsCashierService implements CashierService {
    @Override
    public boolean settleBets(int die1, int die2, DiceGame diceGame) {
        boolean win = (die1 + die2) % 2 == 0;

        List<Player> playerList = diceGame.getPlayerList();

        for (Player player : playerList){
            if (win) {
                player.wins();
            } else {
                player.loses();
                if (player.isBankrupt()) {
                    diceGame.removePlayer(player);
                }
            }
        }

        return win;
    }
}
