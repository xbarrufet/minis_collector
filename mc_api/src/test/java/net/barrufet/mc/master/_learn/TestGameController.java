package net.barrufet.mc.master._learn;


import net.barrufet.mc.master.controller.GameController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
public class TestGameController {



//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockitoBean
//    private GameService gameService;
//
//    private final Game combatPatrol = new Game().withId("CP").withName("Combat Patrol");
//    private final Game spearHead = new Game().withId("SH").withName("Spear Head");
//    private final GameEdition combatPatrol_1 = new GameEdition().withId("CP_1").withGame(combatPatrol);
//    private final GameEdition combatPatrol_2 = new GameEdition().withId("CP_2").withGame(combatPatrol);
//    private final GameEdition spearhead_1 = new GameEdition().withId("SH_1").withGame(spearHead);
//
//    List<Game> games = List.of(combatPatrol, spearHead);
//
//
//    @Test
//    public void get_all_games() throws Exception {
//
//        given(gameService.getAllGames()).willReturn(games);
//
//        mvc.perform(get("/api/v1/games")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].gameName", is(combatPatrol.getName())));
//    }
//
//    @Test
//    public void given_gameId_get_game() throws Exception {
//
//        given(gameService.getGameById("CP")).willReturn(combatPatrol);
//
//        mvc.perform(get("/api/v1/games/CP")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.gameName", is(combatPatrol.getName())));
//    }
//

}
