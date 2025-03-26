package net.barrufet.mc.master.controllers;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.barrufet.mc.master.model.Game;
import net.barrufet.mc.master.repositories.GameEditionRepository;
import net.barrufet.mc.master.repositories.GameRepository;
import net.barrufet.mc.master.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGameController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameEditionRepository gameEditionRepository;

    @Autowired
    private GameService gameService;



    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.3")
            .withDatabaseName("testdb")
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


    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        gameEditionRepository.deleteAll();
        gameRepository.deleteAll();
        g1=gameRepository.save(g1);
        g2=gameRepository.save(g2);
    }

    // https://www.baeldung.com/rest-assured-response


    @Test
    public void test_given_GET_all_games_request_then_get_all_games() throws Exception {


        given()
                .contentType(ContentType.JSON)
                .when()
                    .get("/master/api/v1/games")
                .then()
                    .statusCode(200)
                    .body("size()", is(2))
                    .body("[0].name", is(g1.getName()))
                    .body("[0].description", is(g1.getDescription()))
                    .body("[1].name", is(g2.getName()));
        }

    @Test
    public void test_given_GET_game_when_valid_game_id_then_get_game() throws Exception {

                given()
                .contentType(ContentType.JSON).when()
                .get("/master/api/v1/games/"+g1.getId())
                .then()
                .statusCode(200)
                .body("name", is(g1.getName()))
                .body("description", is(g1.getDescription()));

    }

    @Test
    public void test_given_GET_game_when_nonexisting_id_then_exception() throws Exception {
        UUID nonExistingId = UUID.randomUUID();
        given()
                .contentType(ContentType.JSON).when()
                .get("/master/api/v1/games/" + nonExistingId)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());


    }


    @Test
    public void test_given_GET_game_when_invalid_id_then_exception() throws Exception {

        given()
                .contentType(ContentType.JSON).when()
                .get("/master/api/v1/games/" + "NON_EXISTING_ID")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());


    }


    @Test
    public void test_when_post_valid_game_then_game_is_persisted_and_returned() throws Exception {
        Game game = new Game().withName("game 3").withDescription("game 3 description");
        given()
                .contentType(ContentType.JSON)
                .body(game)
                .when()
                .post("/master/api/v1/games")
                .then()
                .statusCode(200)
                .body("$",hasKey("id"))
                .body("name", is(game.getName()))
                .body("description", is(game.getDescription()));
    }


}