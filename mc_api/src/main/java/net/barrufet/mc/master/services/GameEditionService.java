package net.barrufet.mc.master.services;

import net.barrufet.mc.master.exceptions.GameEditionNotFoundException;
import net.barrufet.mc.master.exceptions.GameNotFoundException;
import net.barrufet.mc.master.model.Game;
import net.barrufet.mc.master.model.GameEdition;

import java.util.List;
import java.util.UUID;

public interface GameEditionService {


    GameEdition getGameEditionById(Long id) throws GameEditionNotFoundException;
    List<GameEdition> getAllGameEditions();
    List<GameEdition> getAllGameEditionsByGameId(Long gameId) throws GameNotFoundException;

}
