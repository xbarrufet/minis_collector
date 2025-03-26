package net.barrufet.mc.master.services;

import net.barrufet.mc.master.exceptions.FactionNotFoundException;
import net.barrufet.mc.master.exceptions.GameEditionNotFoundException;
import net.barrufet.mc.master.model.Faction;
import net.barrufet.mc.master.model.GameEdition;
import net.barrufet.mc.master.repositories.FactionRepository;
import net.barrufet.mc.master.repositories.GameEditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FactionServiceImpl implements FactionService{

    private FactionRepository factionRepository;
    private GameEditionRepository gameEditionRepository;

    public FactionServiceImpl(FactionRepository factionRepository, GameEditionRepository gameEditionRepository) {
        this.factionRepository = factionRepository;
        this.gameEditionRepository = gameEditionRepository;
    }

    @Override
    public List<Faction> getAllFactionsByGameEditionId(UUID gameEditionId) throws GameEditionNotFoundException {
        return this.factionRepository.findByGameEditionId(gameEditionId);
    }

    @Override
    public Faction getFactionById(UUID factionId) throws FactionNotFoundException{
       return this.factionRepository.findById(factionId).orElseThrow( () -> new FactionNotFoundException(factionId.toString()));
    }

    @Override
    public Faction persistFactionOnGameEdition(Faction faction, UUID gameEditionId) throws GameEditionNotFoundException{
        GameEdition gameEdition = gameEditionRepository.findById(gameEditionId).orElseThrow( () -> new GameEditionNotFoundException(gameEditionId.toString()));
        faction.setGameEdition(gameEdition);
        return factionRepository.save(faction);
    }


}
