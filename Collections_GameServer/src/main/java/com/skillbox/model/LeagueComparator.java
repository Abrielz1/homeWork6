package com.skillbox.model;

import java.util.Comparator;

import static com.skillbox.model.League.BRONZE;
import static com.skillbox.model.League.DIAMOND;
import static com.skillbox.model.League.GOLD;
import static com.skillbox.model.League.PLATINUM;
import static com.skillbox.model.League.PRACTICE;
import static com.skillbox.model.League.PRO;
import static com.skillbox.model.League.SILVER;

public class LeagueComparator implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {

        if ((player1.getLeague() == PRO || player1.getLeague() == GOLD)
                && (player2.getLeague() == DIAMOND || player2.getLeague() == PLATINUM
                || player2.getLeague() == SILVER || player2.getLeague() == BRONZE
                || player2.getLeague() == PRACTICE)) {
            return 1;
        } else if ((player2.getLeague() == PRO || player2.getLeague() == GOLD)
                && (player1.getLeague() == DIAMOND || player1.getLeague() == PLATINUM
                || player1.getLeague() == SILVER || player1.getLeague() == BRONZE
                || player1.getLeague() == PRACTICE)) {
            return -1;
        } else {
            return 0;
        }
    }
}
