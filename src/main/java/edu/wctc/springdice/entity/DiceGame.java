package edu.wctc.springdice.entity;

import java.util.ArrayList;
import java.util.List;

public class DiceGame {
    private int activePlayerIndex = -1;
    private List<Player> playerList = new ArrayList<>();

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public Player getCurrentPlayer() {
        if (activePlayerIndex >= 0 && activePlayerIndex < playerList.size()) {
            return playerList.get(activePlayerIndex);
        }
        return null;
    }

    public boolean hasPlayers() {
        return !playerList.isEmpty();
    }

    public Player getNextPlayer() {
        activePlayerIndex++;
        return getCurrentPlayer();
    }

    public List<Player> getPlayerList() {
        List<Player> newList = new ArrayList<>();
        newList.addAll(playerList);
        return newList;
    }

    public void removeActivePlayer() {
        playerList.remove(activePlayerIndex);
        activePlayerIndex--;
    }


    public void removePlayer(Player player) {
        playerList.remove(player);
    }

    public void startRound() {
        activePlayerIndex = -1;
    }
}
