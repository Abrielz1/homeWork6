package com.skillbox.service;

import com.skillbox.model.League;
import com.skillbox.model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import static com.skillbox.model.League.GOLD;
import static com.skillbox.model.Race.ELF;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class LeagueManagerTest {
    private LeagueManager leagueManager;

    @Before
    public void setUp() throws Exception {
        Class<?> stackInterface = LeagueManager.class;
        Reflections reflections = new Reflections("com.skillbox.service", new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());
        for (Class<?> clazz : classes) {
            HashSet<Class<?>> interfaces = new HashSet<>(Arrays.asList(clazz.getInterfaces()));
            for (Class<?> i : interfaces) {
                if (i.equals(stackInterface)) {
                    Object o = clazz.newInstance();
                    leagueManager = (LeagueManager) o;
                }
            }
        }
    }

    @Test
    public void testAddPlayer() {
        Player player = this.generatePlayer();
        leagueManager.addPlayer(player);

        assertEquals(player.getNickName(), leagueManager.getPlayer(player.getNickName()).getNickName());
        assertEquals(player.getLeague(), leagueManager.getPlayer(player.getNickName()).getLeague());
        assertEquals(player.getRace(), leagueManager.getPlayer(player.getNickName()).getRace());
        assertEquals(player.getPoints(), leagueManager.getPlayer(player.getNickName()).getPoints());

        assertEquals(player, leagueManager.getPlayer(player.getNickName()));
    }

    @Test
    public void testRemovePlayer() {
        Player player = this.generatePlayer();
        leagueManager.addPlayer(player);

        assertEquals(player.getNickName(), leagueManager.getPlayer(player.getNickName()).getNickName());
        assertEquals(player.getLeague(), leagueManager.getPlayer(player.getNickName()).getLeague());
        assertEquals(player.getRace(), leagueManager.getPlayer(player.getNickName()).getRace());
        assertEquals(player.getPoints(), leagueManager.getPlayer(player.getNickName()).getPoints());

        assertEquals(player, leagueManager.getPlayer(player.getNickName()));

        leagueManager.removePlayer(player);
        assertNull(leagueManager.getPlayer(player.getNickName()));
    }

    @Test
    public void testGetPlayer() {
        Player player = this.generatePlayer();
        leagueManager.addPlayer(player);
        Player actual = leagueManager.getPlayer(player.getNickName());
        Assert.assertEquals(player, actual);
    }

    @Test
    public void testGetAllPlayers() {
        Player[] players = new Player[10];

        for (int i = 0; i < players.length; i++) {
            Player player = new Player("NickNmae" + i, i * 10 + i, GOLD ,ELF);
            players[i] = player;
            leagueManager.addPlayer(player);
        }

        assertArrayEquals(players, leagueManager.getAllPlayers());
    }

    @Test
    public void testGetPlayersByRace() {
        Player[] players = new Player[10];

        for (int i = 0; i < players.length; i++) {
            Player player = new Player("NickNmae" + i, i * 10 + i, GOLD ,ELF);
            players[i] = player;
            leagueManager.addPlayer(player);
        }

        assertArrayEquals(players, leagueManager.getPlayers(ELF));
    }

    @Test
    public void testGetPlayersByLeague() {
        Player[] players = new Player[10];

        for (int i = 0; i < players.length; i++) {
            Player player = new Player("NickNmae" + i, i * 10 + i, GOLD ,ELF);
            players[i] = player;
            leagueManager.addPlayer(player);
        }

        assertArrayEquals(players, leagueManager.getPlayers(GOLD));
    }

    @Test
    public void testAddPoints() {
        Player player = generatePlayer();
        int expected = player.getPoints();
        leagueManager.addPlayer(player);
        int points = 10;
        expected += points;
        leagueManager.addPoints(player.getNickName(), points);
        Player actual = leagueManager.getPlayer(player.getNickName());
        Assert.assertEquals(expected, actual.getPoints());
    }

    Player generatePlayer() {
        return new Player("name", 1, League.BRONZE, ELF);
    }
}
