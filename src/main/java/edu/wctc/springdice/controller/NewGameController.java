package edu.wctc.springdice.controller;

import edu.wctc.springdice.entity.DiceGame;
import edu.wctc.springdice.entity.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("diceGame")
public class NewGameController {

    @RequestMapping("/addPlayer")
    public String addPlayer(@ModelAttribute("diceGame") DiceGame diceGame,
                            @ModelAttribute("newPlayer") Player newPlayer) {
        diceGame.addPlayer(newPlayer);
        return "player-register";
    }

    @RequestMapping("/newGame")
    public String createNewGame(@ModelAttribute("diceGame") DiceGame diceGame,
                                Model model) {
        model.addAttribute("newPlayer", new Player());
        return "player-register";
    }

    @ModelAttribute("diceGame")
    public DiceGame diceGame() {
        DiceGame game = new DiceGame();
        return game;
    }
}
