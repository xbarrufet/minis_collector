package net.barrufet.mc.master.services;

import net.barrufet.mc.master.exceptions.GameEditionNotFoundException;
import net.barrufet.mc.master.exceptions.GameNotFoundException;
import net.barrufet.mc.master.model.*;
import net.barrufet.mc.master.repositories.*;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Override
    public Game getGameById(UUID id) throws GameNotFoundException{
        return this.gameRepository.findById(id).orElseThrow(
                () -> new GameNotFoundException(id.toString()));
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game persistGame(Game game) {
        return this.gameRepository.save(game);
    }


    }

