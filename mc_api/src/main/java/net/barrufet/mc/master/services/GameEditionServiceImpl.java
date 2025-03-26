package net.barrufet.mc.master.services;

import net.barrufet.mc.master.exceptions.GameEditionNotFoundException;
import net.barrufet.mc.master.exceptions.GameNotFoundException;
import net.barrufet.mc.master.model.Game;
import net.barrufet.mc.master.model.GameEdition;
import net.barrufet.mc.master.repositories.GameEditionRepository;
import net.barrufet.mc.master.repositories.GameRepository;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameEditionServiceImpl implements GameEditionService {


    private final GameEditionRepository gameEditionRepository;


    public GameEditionServiceImpl( GameEditionRepository gameEditionRepository) {
        this.gameEditionRepository = gameEditionRepository;
    }


    @Override
    public GameEdition getGameEditionById(Long id) throws GameEditionNotFoundException {
        return this.gameEditionRepository.findById(id).orElseThrow(
                () -> new GameEditionNotFoundException(String.valueOf(id)));
    }

    @Override
    public List<GameEdition> getAllGameEditions() {
       return this.gameEditionRepository.findAll();
    }

    @Override
    public List<GameEdition> getAllGameEditionsByGameId(Long gameId) throws GameNotFoundException {
        return this.gameEditionRepository.findByGameId(gameId);
        
    }


}
