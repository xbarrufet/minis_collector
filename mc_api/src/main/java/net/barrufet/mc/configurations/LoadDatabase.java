package net.barrufet.mc.configurations;

import net.barrufet.mc.master.model.Game;
import net.barrufet.mc.master.model.GameEdition;
import net.barrufet.mc.master.repositories.GameEditionRepository;
import net.barrufet.mc.master.repositories.GameRepository;
import net.barrufet.mc.master.repositories.FactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(GameRepository gameRepository, GameEditionRepository gameEditionRepository, FactionRepository factionRepository) {


        //Faction fStormcast = new Faction("stormcast_eternals_AoS_4","Stormcast Eternals", "Order","{'rules':[]}", geAos4);
        //Faction fStormcast = new Faction("stormcast_eternals_AoS_4","Stormcast Eternals", "Order","{'rules':[]}");
        // Faction fStormcast = new Faction("stormcast_eternals_AoS_4","Stormcast Eternals", "Order");
        return args -> {

            factionRepository.deleteAll();
            Game gAos = new Game().withName("Age of Sigmar").withDescription("Age of Sigmar is tabletop game");
            Game g40k = new Game().withName("Warhammer 40.000r").withDescription("Warhammer 40.000 is tabletop game");
            Game gMesbg = new Game().withName("Middle Earth Strategic Battle game").withDescription("Middle Earth Strategic Battle game");
            Game gCombatPatrol = new Game().withName("Combat Patrol").withDescription("Warhammer 40.000 Combat Patrol");
            Game gSpearHead = new Game().withName("SpearHead").withDescription("Age of Sigmar SpearHead");

            Optional<Game> g1 = gameRepository.findByNameIgnoreCase(gAos.getName());
            if (g1.isEmpty()) {
                log.info("Preloading saving {}", gAos);
                        gAos = gameRepository.save(gAos);
                        GameEdition geAos4 = new GameEdition().withName("Age of Sigmar 4th Edition").withGame(gAos);
                        gameEditionRepository.save(geAos4);
            }

            Optional<Game> g2 = gameRepository.findByNameIgnoreCase(g40k.getName());
            if (g2.isEmpty()) {
                log.info("Preloading saving {}", g40k);
                g40k= gameRepository.save(g40k);
                GameEdition ge40k_10 = new GameEdition().withName("40K_10").withGame(g40k);
                gameEditionRepository.save(ge40k_10);
            }

            Optional<Game> g3 =  gameRepository.findByNameIgnoreCase(gMesbg.getName());
            if (g3.isEmpty()) {

                log.info("Preloading saving {}", gMesbg);
                gMesbg= gameRepository.save(gMesbg);
                GameEdition geMesbg_2024 = new GameEdition().withName("MESBG_2024").withGame(gMesbg);
                gameEditionRepository.save(geMesbg_2024);
            }

            Optional<Game> g4 =    gameRepository.findByNameIgnoreCase(gCombatPatrol.getName());
            if (g4.isEmpty()) {
                log.info("Preloading saving {}", gCombatPatrol);
                gCombatPatrol= gameRepository.save(gCombatPatrol);
                GameEdition geCP_10 = new GameEdition().withName("Combat Patrol 40K 10th").withGame(gCombatPatrol);
                gameEditionRepository.save(geCP_10);
            }

            Optional<Game> g5 = gameRepository.findByNameIgnoreCase(gSpearHead.getName());
            if (g5.isEmpty()) {
                log.info("Preloading saving {}", gSpearHead);
                gSpearHead = gameRepository.save(gSpearHead);
                GameEdition geSH_4 = new GameEdition().withName("SpaearHead AoS 4th").withGame(gSpearHead);
                gameEditionRepository.save(geSH_4);
            }
        };
    }
}
