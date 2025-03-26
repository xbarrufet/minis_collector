package net.barrufet.mc.master.controllers;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.barrufet.mc.master.model.Game;
import net.barrufet.mc.master.model.GameEdition;
import net.barrufet.mc.master.repositories.GameEditionRepository;
import net.barrufet.mc.master.repositories.GameRepository;
import net.barrufet.mc.master.services.GameEditionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGameEditionController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameEditionRepository gameEditionRepository;

    @Autowired
    private GameEditionService gameService;



    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3")
            .withDatabaseName("test_db")
            .withUsername("user")
            .withPassword("password")
            .withReuse(true);

    static {
        postgres.start();
        System.setProperty("DB_URL", postgres.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgres.getUsername());
        System.setProperty("DB_PASSWORD", postgres.getPassword());
    }
    @LocalServerPort
    private Integer port;


    Game g1 = new Game().withName("game 1").withDescription("game 1 description");
    Game g2 = new Game().withName("game 2").withDescription("game 2 description");

    GameEdition ge1_1 = new GameEdition().withName("game 1 edition 1");
    GameEdition ge1_2 = new GameEdition().withName("game 1 edition 2");
    GameEdition ge2_1 = new GameEdition().withName("game 2 edition 1");

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        gameEditionRepository.deleteAll();
        gameRepository.deleteAll();
        g1=gameRepository.save(g1);
        g2=gameRepository.save(g2);
        ge1_1.setGame(g1);
        ge1_2.setGame(g1);
        ge2_1.setGame(g2);
        ge1_1=gameEditionRepository.save(ge1_1);
        ge1_2=gameEditionRepository.save(ge1_2);
        ge2_1=gameEditionRepository.save(ge2_1);
    }

    // https://www.baeldung.com/rest-assured-response


    @Test
    public void test_given_GET_all_gameEditions_request_then_get_all_gamesEditions() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .when()
                    .get("/master/api/v1/games/gameEditions")
                .then()
                    .statusCode(200)
                    .body("size()", is(3))
                    .body("[0].name", is(ge1_1.getName()))
                    .body("[1].name", is(ge1_2.getName()))
                    .body("[2].name", is(ge2_1.getName()));
        }

    @Test
    public void test_given_GET_game_gameEdtions_when_exisiting_gameid_request_then_get_game_gamesEditions() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/master/api/v1/games/"+g1.getId() + "/gameEditions")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2))
                .body("[0].name", is(ge1_1.getName()))
                .body("[1].name", is(ge1_2.getName()));

    }

    @Test
    public void test_given_GET_game_gameEdtions_when_non_exisiting_gameid_request_empty_list() throws Exception {

        long nonExisingId =99999L;
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/master/api/v1/games/" +nonExisingId + "/gameEditions")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(0));


    }


//    @Test
//    public void test_given_POST_gameEdition_when_valid_gameEdition_and_valid_game_then_gameEdition_is_persisted_and_returned() throws Exception {
//        GameEdition newGameEdition = new GameEdition().withName("game 2 edition 2").withGame(g2);
//        given()
//                .contentType(ContentType.JSON)
//                .body(newGameEdition)
//                .when()
//                .post("/master/api/v1/games/" + g2.getId() + "/gameEditions")
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("$", hasKey("id"))
//                .body("name", is(newGameEdition.getName()));
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("/master/api/v1/games/"+g2.getId() + "/gameEditions")
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("size()", is(2))
//                .body("[1].name", is(newGameEdition.getName()));
//
//    }
//
//    @Test
//    public void test_given_POST_gameEdition_when_post_valid_gameEdition_and_non_exising_game_then_expcetion() throws Exception {
//        Game nonExistingGame = new Game().withId(UUID.randomUUID());
//        GameEdition newGameEdition = new GameEdition().withName("game 2 edition 2").withGame(nonExistingGame);
//        given()
//                .contentType(ContentType.JSON)
//                .body(newGameEdition)
//                .when()
//                .post("/master/api/v1/games/" + nonExistingGame.getId() + "/gameEditions")
//                .then()
//                .statusCode(HttpStatus.NOT_FOUND.value());
//
//    }
//
//
//    @Test
//    public void test_when_PUT_gameEdition_when_valid_gameEdition_then_gameEdition_is_persisted() throws Exception {
//        GameEdition upadtedGameEdition=ge2_1.withName("new game 2 edition 1 name");
//        given()
//                .contentType(ContentType.JSON)
//                .body(upadtedGameEdition)
//                .when()
//                .put("/master/api/v1/games/gameEditions/" + ge2_1.getId())
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("id", is(ge2_1.getId().toString()))
//                .body("name", is(upadtedGameEdition.getName()));
//
//    }



}