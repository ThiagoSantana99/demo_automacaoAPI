package br.com.demo.dogapi.tests;

import br.com.demo.dogapi.model.BreedsListResponse;
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
 * Testes automatizados para o endpoint {@code GET /breeds/list/all}.
 * <p>
 * A classe valida status HTTP, estrutura do payload e presença de dados mínimos
 * que indicam disponibilidade do catálogo de raças.
 *
 * @author Thiago Santana
 * @version 1.0
 */
@Epic("Dog API")
@Feature("Breed catalog")
class BreedsListAllTest extends BaseDogApiTest {

    /**
     * Verifica se a API retorna o catálogo completo de raças com payload válido.
     */
    @Test
    @Owner("QA")
    @Severity(SeverityLevel.CRITICAL)
    @Story("GET /breeds/list/all")
    @DisplayName("Deve retornar a lista completa de raças")
    @Description("Valida que a API retorna o payload esperado com status de sucesso e um catálogo não vazio.")
    void shouldReturnAllBreeds() {
        var response = get("/breeds/list/all");
        AllureSupport.attachResponse("breeds-list-all", response);

        var payload = response.as(BreedsListResponse.class);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(payload.status()).isEqualTo("success");
        assertThat(payload.message()).isNotEmpty();
        assertThat(payload.message()).containsKey("hound");
        assertThat(payload.message().get("hound")).isNotNull();
    }
}
