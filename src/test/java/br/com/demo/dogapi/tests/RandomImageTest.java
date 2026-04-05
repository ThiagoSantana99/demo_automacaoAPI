package br.com.demo.dogapi.tests;

import br.com.demo.dogapi.model.RandomImageResponse;
import br.com.demo.dogapi.support.AllureSupport;
import br.com.demo.dogapi.support.BaseDogApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testes automatizados para o endpoint {@code GET /breeds/image/random}.
 * <p>
 * A classe valida se a API entrega uma imagem aleatória com o formato esperado.
 *
 * @author Thiago Santana
 * @version 1.0
 */
@Epic("Dog API")
@Feature("Random images")
class RandomImageTest extends BaseDogApiTest {

    /**
     * Verifica se a API retorna uma URL válida para uma imagem aleatória.
     */
    @Test
    @Owner("QA")
    @Severity(SeverityLevel.CRITICAL)
    @Story("GET /breeds/image/random")
    @DisplayName("Deve retornar uma imagem aleatória de cão")
    @Description("Valida status HTTP, formato da resposta e URL da imagem retornada.")
    void shouldReturnRandomImage() {
        var response = get("/breeds/image/random");
        AllureSupport.attachResponse("random-image", response);

        var payload = response.as(RandomImageResponse.class);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(payload.status()).isEqualTo("success");
        assertThat(payload.message()).isNotBlank();
        assertThat(payload.message())
                .startsWith("https://")
                .contains("dog.ceo");
    }
}
