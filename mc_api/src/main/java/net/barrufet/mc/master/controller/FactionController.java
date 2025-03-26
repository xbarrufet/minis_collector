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
    public List<FactionHeaderDTO> getFactionsByGameEditionId(@PathVariable Long gameEditionId) {
        List<Faction> factions = factionService.getAllFactionsByGameEditionId(gameEditionId);
        return factions.stream()
                .map(this::convertFactionToFactionHeaderDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/api/v1/game/gameEdition/factions/{factionId}")
    public FactionDTO getFactionByd(@PathVariable Long factionId) {
        Faction faction = factionService.getFactionById(factionId);
        return this.convertFactionToFactionDTO(faction);
    }


    @PostMapping("/api/v1/game/gameEdition/{gameEditionId}/factions")
    public FactionHeaderDTO createFactionsByGameEditionId(@PathVariable Long gameEditionId,@RequestBody FactionDTO factionDTO) {
        Faction faction = convertFactionDTOToFaction(factionDTO);
        Faction newFaction = factionService.persistFactionOnGameEdition(faction,gameEditionId);
        return convertFactionToFactionHeaderDTO(newFaction);
    }



    private FactionHeaderDTO convertFactionToFactionHeaderDTO(Faction faction) {
        return modelMapper.map(faction, FactionHeaderDTO.class);
    }

    private FactionDTO convertFactionToFactionDTO(Faction faction) {
        return modelMapper.map(faction, FactionDTO.class);
    }

    private Faction convertFactionDTOToFaction(FactionDTO factionDTO) {
        return modelMapper.map(factionDTO, Faction.class);
    }
}
