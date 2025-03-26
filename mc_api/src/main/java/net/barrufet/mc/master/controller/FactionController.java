package net.barrufet.mc.master.controller;

import net.barrufet.mc.master.controller.dtos.FactionDTO;
import net.barrufet.mc.master.controller.dtos.FactionHeaderDTO;
import net.barrufet.mc.master.model.Faction;
import net.barrufet.mc.master.services.FactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("master")
public class FactionController {

    private final FactionService factionService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    FactionController(FactionService factionService) {
        this.factionService = factionService;
    }


    @GetMapping("/api/v1/game/gameEdition/{gameEditionId}/factions")
    public List<FactionHeaderDTO> getFactionsByGameEditionId(@PathVariable UUID gameEditionId) {
        List<Faction> factions = factionService.getAllFactionsByGameEditionId(gameEditionId);
        return factions.stream()
                .map(this::convertFactionToFactionHeaderDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    @PostMapping("/api/v1/game/gameEdition/{gameEditionId}/factions")
    public FactionHeaderDTO createFactionsByGameEditionId(@PathVariable UUID gameEditionId,@RequestBody FactionDTO factionDTO) {
        Faction faction = convertFactionDTOToFaction(factionDTO);
        Faction newFaction = factionService.persistFactionOnGameEdition(faction,gameEditionId);
        return convertFactionToFactionHeaderDTO(newFaction);
    }



    private FactionHeaderDTO convertFactionToFactionHeaderDTO(Faction faction) {
        return modelMapper.map(faction, FactionHeaderDTO.class);
    }

    private Faction convertFactionDTOToFaction(FactionDTO factionDTO) {
        return modelMapper.map(factionDTO, Faction.class);
    }
}
