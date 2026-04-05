package br.com.demo.dogapi.support;

import br.com.demo.dogapi.config.DogApiConfig;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

/**
 * Classe base para os testes da Dog API.
 * <p>
 * Responsável por configurar o RestAssured uma única vez e fornecer operações
 * utilitárias compartilhadas entre os cenários de teste.
 *
 * @author Thiago Santana
 * @version 1.0
 */
public abstract class BaseDogApiTest {

    /**
     * Configura a base da API e habilita o log de requisições e respostas quando uma validação falhar.
     */
    @BeforeAll
    static void configureRestAssured() {
        RestAssured.baseURI = DogApiConfig.baseUri();
        RestAssured.basePath = DogApiConfig.basePath();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * Executa uma chamada HTTP GET em um caminho relativo da Dog API.
     *
     * @param path caminho relativo do endpoint que será chamado
     * @return resposta HTTP retornada pela API
     */
    @Step("Executar GET {path}")
    protected Response get(String path) {
        var requestUri = RestAssured.baseURI + RestAssured.basePath + path;
        AllureSupport.attachRequest("dog-api", "GET", requestUri, ContentType.JSON.toString());

        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }
}
