package edu.wctc.springdice.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SixSidedDieRollerService implements DieRollerService {

    private Random rand = new Random();

    @Override
    public int rollDie() {
        return rand.nextInt(6) + 1;
    }
}
