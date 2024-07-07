package com.skillbox.service;

import com.skillbox.model.League;
import com.skillbox.model.Player;
import com.skillbox.model.Race;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LeagueManagerImpl implements LeagueManager {

    private List<Player> map = new CopyOnWriteArrayList<>();

    @Override
    public void addPlayer(Player player) {
        map.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        map.remove(player);
    }

    @Override
    public Player getPlayer(String name) {
        return map.stream()
                .filter(p -> p.getNickName().equals(name))
                .findFirst()
                .orElse(null);

    }

    @Override
    public Player[] getAllPlayers() {
        return map.stream().toArray(Player[]::new);
    }

    @Override
    public Player[] getPlayers(League league) {
        return map.stream()
                .filter(p -> p.getLeague().equals(league))
                .toArray(Player[]::new);
    }

    @Override
    public Player[] getPlayers(Race race) {
        return map.stream()
                .filter(p -> p.getRace().equals(race))
                .toArray(Player[]::new);
    }

    @Override
    public void addPoints(String name, int points) {
        Player player = this.getPlayer(name);
        player.setPoints(player.getPoints() + points);
        map.remove(player);
        map.add(player);
    }
}
