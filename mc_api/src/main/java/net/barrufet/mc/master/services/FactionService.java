package net.barrufet.mc.master.services;

import net.barrufet.mc.master.model.Faction;

import java.util.List;
import java.util.UUID;

public interface FactionService {

    List<Faction> getAllFactionsByGameEditionId(UUID gameEditionId);
    Faction getFactionById(UUID factionId);
    Faction persistFactionOnGameEdition(Faction faction,UUID gameEditionId);
}
