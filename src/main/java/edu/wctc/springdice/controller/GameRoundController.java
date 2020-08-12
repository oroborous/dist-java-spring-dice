package edu.wctc.springdice.controller;

import edu.wctc.springdice.entity.DiceGame;
import edu.wctc.springdice.entity.Player;
import edu.wctc.springdice.service.CashierService;
import edu.wctc.springdice.service.DieRollerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("diceGame")
public class GameRoundController {

    @Autowired
    private DieRollerService dieRollerService;

    @Autowired
    private CashierService cashierService;

    @RequestMapping("/cashOut")
    public String cashOut(@ModelAttribute("diceGame") DiceGame diceGame) {
        diceGame.removeActivePlayer();
        return "forward:/placeBet";
    }

    @RequestMapping("/placeBet")
    public String placeBet(@ModelAttribute("diceGame") DiceGame diceGame,
                           @ModelAttribute("bettingPlayer") Player emptyPlayerObjWithBetAmount,
                           Model model) {
        Player currentPlayer = diceGame.getCurrentPlayer();
        if (currentPlayer != null) {
            currentPlayer.setBet(emptyPlayerObjWithBetAmount.getBet());
        }

        Player nextPlayer = diceGame.getNextPlayer();

        if (nextPlayer != null) {
            model.addAttribute("bettingPlayer", nextPlayer);

            return "place-bet";
        }

        // No next player means end of round
        if (diceGame.hasPlayers()) {
            int die1 = dieRollerService.rollDie();
            int die2 = dieRollerService.rollDie();
            model.addAttribute("die1", die1);
            model.addAttribute("die2", die2);

            boolean win = cashierService.settleBets(die1, die2, diceGame);
            model.addAttribute("win", win);

            return "roll-dice";
        }

        return "game-over";
    }

    @RequestMapping("/startRound")
    public String startRound(@ModelAttribute("diceGame") DiceGame diceGame) {
        if (diceGame.hasPlayers()) {
            diceGame.startRound(); // sets active player to -1
            return "forward:/placeBet";
        }

        return "game-over";
    }
}
