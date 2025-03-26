package net.barrufet.mc.master._learn;


import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGameServiceTestContainer {

//    @Autowired
//    private GameRepository gameRepository;
//
//    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3")
//            .withDatabaseName("testdb")
//            .withUsername("user")
//            .withPassword("password")
//            .withReuse(true);
//
//    static {
//        postgres.start();
//        System.setProperty("DB_URL", postgres.getJdbcUrl());
//        System.setProperty("DB_USERNAME", postgres.getUsername());
//        System.setProperty("DB_PASSWORD", postgres.getPassword());
//    }
//    @LocalServerPort
//    private Integer port;
//
//    private Game g1 = new Game().withId("G1").withName("game 1");
//
//    @Test
//    void shouldCreateUser() {
//        gameRepository.save(g1);
//        Optional<Game> found = gameRepository.findById("G1");
//        assertTrue(found.isPresent());
//        assertEquals("G1", found.get().getId());
//    }
}
