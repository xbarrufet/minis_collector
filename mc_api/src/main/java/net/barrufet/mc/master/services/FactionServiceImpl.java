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

    private final FactionRepository factionRepository;
    private final GameEditionRepository gameEditionRepository;

    public FactionServiceImpl(FactionRepository factionRepository, GameEditionRepository gameEditionRepository) {
        this.factionRepository = factionRepository;
        this.gameEditionRepository = gameEditionRepository;
    }

    @Override
    public List<Faction> getAllFactionsByGameEditionId(Long gameEditionId) throws GameEditionNotFoundException {
        return this.factionRepository.findByGameEditionId(gameEditionId);
    }

    @Override
    public Faction getFactionById(Long factionId) throws FactionNotFoundException{
       return this.factionRepository.findById(factionId).orElseThrow( () -> new FactionNotFoundException(String.valueOf(factionId)));
    }

    @Override
    public Faction persistFactionOnGameEdition(Faction faction, Long gameEditionId) throws GameEditionNotFoundException{
        GameEdition gameEdition = gameEditionRepository.findById(gameEditionId).orElseThrow( () -> new GameEditionNotFoundException(String.valueOf(gameEditionId)));
        faction.setGameEdition(gameEdition);
        return factionRepository.save(faction);
    }


}
