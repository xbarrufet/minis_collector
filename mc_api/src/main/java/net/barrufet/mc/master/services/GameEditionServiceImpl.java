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
    private final GameRepository gameRepository;


    public GameEditionServiceImpl( GameEditionRepository gameEditionRepository, GameRepository gameRepository) {
        this.gameEditionRepository = gameEditionRepository;
        this.gameRepository = gameRepository;
    }


    @Override
    public GameEdition getGameEditionById(UUID id) throws GameEditionNotFoundException {
        return this.gameEditionRepository.findById(id).orElseThrow(
                () -> new GameEditionNotFoundException(id.toString()));
    }

    @Override
    public List<GameEdition> getAllGameEditions() {
       return this.gameEditionRepository.findAll();
    }

    @Override
    public List<GameEdition> getAllGameEditionsByGameId(UUID gameId) throws GameNotFoundException {
        return this.gameEditionRepository.findByGameId(gameId);
        
    }

    @Override
    public GameEdition persisGameEditionOnGame(UUID gameId, GameEdition gameEdition) throws GameNotFoundException {
        Game game = gameRepository.findById(gameId).orElseThrow( () -> new GameNotFoundException(gameId.toString()));
        gameEdition.setGame(game);
        return gameEditionRepository.save(gameEdition);
    }

    @Override
    public GameEdition persistGameEdition(GameEdition gameEdition) throws GameNotFoundException, GameEditionNotFoundException {
        try {
            if(gameEdition.getId()!=null && !gameEditionRepository.existsById(gameEdition.getId())){
                throw new GameEditionNotFoundException(gameEdition.getId().toString());
            }
            if(gameEdition.getGame()==null){
                throw new GameNotFoundException("null");
            }
            if(!gameRepository.existsById(gameEdition.getGame().getId())) {
                throw new GameNotFoundException(gameEdition.getGame().getId().toString());
            }
            return this.gameEditionRepository.save(gameEdition);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new GameNotFoundException(gameEdition.getGame().getId().toString());
        }
    }



}
