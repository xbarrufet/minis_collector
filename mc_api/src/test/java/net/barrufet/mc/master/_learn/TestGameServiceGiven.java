package net.barrufet.mc.master._learn;

import net.barrufet.mc.master.repositories.GameEditionRepository;
import net.barrufet.mc.master.repositories.GameRepository;
import net.barrufet.mc.master.services.GameServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestGameServiceGiven {

    @Mock
    private GameRepository gameRepository;
    @Mock
    private GameEditionRepository gameEditionRepository;
    @InjectMocks
    GameServiceImpl gameService;

//    private Game bbGame;
//
//    @BeforeEach
//    public void setup() {
//        bbGame = new Game()
//                .withId("BB")
//                .withName("Bllod Bowl");
//    }


//    @Test
//    public void givenNewGame_whenSavesGame_returnGame () throws Exception {
//
//        //given(gameRepository.findById("BB")).willReturn(Optional.empty());
//        given(gameRepository.save(bbGame)).willReturn(bbGame);
//
//        Game foundGame = gameService.persistGame(bbGame);
//
//        assertThat(foundGame).isNotNull();
//        assertThat(foundGame).isEqualTo(bbGame);
//
//    }

}
