package com.algaworks.algafood;




import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static io.restassured.RestAssured.when;



@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }
    /*
    @Test
    public void whenLogResponse_thenOK() {
        when()
                .get("/cozinhas")
                .then().log().body().statusCode(200);
    }
    */

    @Test
    public void whenLogResponse_thenOK2() {
        var a = RestAssured.when()
                .get("/cozinhas")
                .then().log().body().statusCode(200);
        System.out.println(a);
    }
    /*
    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        System.out.println("Porta: " + port);
        given()
                .basePath("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }
    */

/*
    @Test
    public void deveConter4Cozinhas_QuandoConsultarCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
                // .when()
               // .get()
                .then()
                .body("", hasSize(4));
//			.body("nome", hasItems("Indiana", "Tailandesa"));
    }

 */
}