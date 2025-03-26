package net.barrufet.mc.master.controller;

import net.barrufet.mc.master.controller.dtos.GameDTO;
import net.barrufet.mc.master.controller.dtos.GameEditionDTO;
import net.barrufet.mc.master.exceptions.GameEditionNotFoundException;
import net.barrufet.mc.master.exceptions.GameNotFoundException;
import net.barrufet.mc.master.model.Game;
import net.barrufet.mc.master.model.GameEdition;
import net.barrufet.mc.master.services.GameEditionService;
import net.barrufet.mc.master.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("master")
public class GameEditionController {


    private final GameEditionService gameEditionService;
    private final GameService gameService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    GameEditionController(GameEditionService gameEditionService, GameService gameService) {
        this.gameEditionService = gameEditionService;
        this.gameService=gameService;
    }

    //***********  GANE EDITIONS SECTION
    @GetMapping("/api/v1/games/{id}/gameEditions")
    public List<GameEditionDTO> getGameEditionsByGameId(@PathVariable Long id) throws GameEditionNotFoundException {
        List<GameEdition> gameEditions =gameEditionService.getAllGameEditionsByGameId(id);
        return gameEditions.stream()
                .map(this::convertGameEditionToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/games/gameEditions")
    public List<GameEditionDTO> getAllGameEditions() throws GameEditionNotFoundException {
        List<GameEdition> gameEditions = gameEditionService.getAllGameEditions();
        return gameEditions.stream()
                .map(this::convertGameEditionToDTO)
                .collect(Collectors.toList());
    }


    private GameEditionDTO convertGameEditionToDTO(GameEdition gameEdition) {
        return modelMapper.map(gameEdition, GameEditionDTO.class);
    }

}











