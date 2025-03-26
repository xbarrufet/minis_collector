package net.barrufet.mc.master.controller;

import net.barrufet.mc.master.controller.dtos.GameDTO;
import net.barrufet.mc.master.controller.dtos.GameEditionDTO;
import net.barrufet.mc.master.exceptions.GameEditionNotFoundException;
import net.barrufet.mc.master.exceptions.GameNotFoundException;
import net.barrufet.mc.master.model.Game;
import net.barrufet.mc.master.model.GameEdition;
import net.barrufet.mc.master.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("master")
public class GameController {


    private final GameService gameService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/api/v1/games")
    List<GameDTO> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return games.stream()
                .map(this::convertGameToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/v1/games")
    GameDTO newGame(@RequestBody Game newGame) {
        Game game = gameService.persistGame(newGame);
        return convertGameToDto(game);
    }

    @GetMapping("/api/v1/games/{id}")
    GameDTO getGameById(@PathVariable UUID id) throws GameNotFoundException {
        Game game = gameService.getGameById(id);
        return convertGameToDto(game);
    }

    @PutMapping("/api/v1/games/{id}")
    GameDTO replaceGame(@RequestBody Game game, @PathVariable UUID id) {
        Game toBeUpdatedGame = game.withId(id);
        Game returnGame = gameService.persistGame(toBeUpdatedGame);
        return convertGameToDto(returnGame);
    }


    private GameDTO convertGameToDto(Game game) {
        if(game.getGameEditions()!=null)
            game.getGameEditions().forEach(gameEdition -> gameEdition.setGame(game));
        GameDTO res = modelMapper.map(game, GameDTO.class);
        if(res.getGameEditions()==null)
            res.setGameEditions(List.of());
        return res;
    }


}











