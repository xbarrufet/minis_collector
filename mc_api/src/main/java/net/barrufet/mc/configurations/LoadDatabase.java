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

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(GameRepository gameRepository, GameEditionRepository gameEditionRepository, FactionRepository factionRepository) {




        //Faction fStormcast = new Faction("stormcast_eternals_AoS_4","Stormcast Eternals", "Order","{'rules':[]}", geAos4);
        //Faction fStormcast = new Faction("stormcast_eternals_AoS_4","Stormcast Eternals", "Order","{'rules':[]}");
        // Faction fStormcast = new Faction("stormcast_eternals_AoS_4","Stormcast Eternals", "Order");
        return args -> {
            Game gAos = new Game().withName("Age of Sigmar").withDescription("Age of Sigmar is tabletop game");
            Game g40k = new Game().withName("Warhammer 40.000r").withDescription("Warhammer 40.000 is tabletop game");
            Game gMesbg = new Game().withName("Middle Earth Strategic Battle game").withDescription("Middle Earth Strategic Battle game");



            log.info("Cleaning gameEditions table");
            gameEditionRepository.deleteAll();
            log.info("Cleaning games table");
            gameRepository.deleteAll();


            log.info("Preloading saving {}", gAos);
            gAos= gameRepository.save(gAos);
            log.info("Preloading saving {}", g40k);
            g40k= gameRepository.save(g40k);
            log.info("Preloading saving {}", gMesbg);
            gMesbg= gameRepository.save(gMesbg);

            GameEdition geAos4 = new GameEdition().withName("Age of Sigmar 4th Edition").withGame(gAos);
            GameEdition ge40k_10 = new GameEdition().withName("40K_10").withGame(g40k);
            GameEdition geMesbg_2024 =new GameEdition().withName("MESBG_2024").withGame(gMesbg);

            log.info("Preloading saving {}",geAos4 );
            gameEditionRepository.save(geAos4);
            log.info("Preloading saving {}",ge40k_10 );
            gameEditionRepository.save(ge40k_10);
            log.info("Preloading saving {}", geMesbg_2024);
            gameEditionRepository.save(geMesbg_2024);

            //log.info("Preloading {}", factionRepository.save(fStormcast));
        };
    }
}
