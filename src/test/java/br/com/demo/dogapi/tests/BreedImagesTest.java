package br.com.demo.dogapi.tests;

import br.com.demo.dogapi.model.BreedImagesResponse;
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
import io.restassured.response.Response;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testes automatizados para o endpoint {@code GET /breed/{breed}/images}.
 * <p>
 * A classe cobre cenários de sucesso e falha para raças conhecidas, formato inválido
 * e raça inexistente, garantindo que o contrato do endpoint permaneça estável.
 *
 * @author Thiago Santana
 * @version 1.0
 */
@Epic("Dog API")
@Feature("Breed images")
class BreedImagesTest extends BaseDogApiTest {

    /**
     * Verifica se a API lista imagens válidas para a raça {@code hound}.
     * <p>
     * Responsabilidade:
     * confirmar que o endpoint responde com sucesso, retorna um payload funcional e
     * entrega pelo menos uma URL sintaticamente válida.
     */
    @Test
    @Owner("QA")
    @Severity(SeverityLevel.CRITICAL)
    @Story("GET /breed/{breed}/images")
    @DisplayName("Deve listar imagens da raça hound")
    @Description("Valida status HTTP, formato da resposta e consistência dos URLs retornados para uma raça conhecida.")
    void shouldListImagesForKnownBreed() {
        var response = get("/breed/hound/images");
        AllureSupport.attachResponse("breed-images", response);

        var payload = response.as(BreedImagesResponse.class);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(payload.status()).isEqualTo("success");
        assertThat(payload.message()).isNotEmpty();
        assertThat(payload.message())
                .allMatch(url -> {
                    URI uri = URI.create(url);
                    return uri.getScheme() != null && uri.getHost() != null;
                });
    }

    /**
     * Verifica se a API rejeita um valor em formato inválido para a raça.
     * <p>
     * Responsabilidade:
     * validar a robustez do endpoint quando recebe uma string fora do padrão esperado.
     */
    @Test
    @Owner("QA")
    @Severity(SeverityLevel.NORMAL)
    @Story("GET /breed/{breed}/images")
    @DisplayName("Deve retornar erro para formato inválido de raça")
    @Description("Valida que a API responde com erro quando o parâmetro de raça é enviado em formato inválido.")
    void shouldReturnErrorForInvalidBreedFormat() {
        var response = get("/breed/hound--walker/images");
        AllureSupport.attachResponse("breed-images-invalid-format", response);

        assertErrorResponse(response);
    }

    /**
     * Verifica se a API rejeita uma raça inexistente.
     * <p>
     * Responsabilidade:
     * garantir que a API responda com erro para um termo que não representa uma raça suportada.
     */
    @Test
    @Owner("QA")
    @Severity(SeverityLevel.NORMAL)
    @Story("GET /breed/{breed}/images")
    @DisplayName("Deve retornar erro para raça inexistente")
    @Description("Valida que a API responde com erro quando uma raça inexistente é informada.")
    void shouldReturnErrorForNonexistentBreed() {
        var response = get("/breed/papagaio/images");
        AllureSupport.attachResponse("breed-images-nonexistent", response);

        assertErrorResponse(response);
    }

    /**
     * Verifica se a API rejeita a raça {@code hound-walker}.
     * <p>
     * Responsabilidade:
     * confirmar que o endpoint responde com erro quando a raça composta informada não existe
     * no catálogo suportado pela Dog API.
     */
    @Test
    @Owner("QA")
    @Severity(SeverityLevel.NORMAL)
    @Story("GET /breed/{breed}/images")
    @DisplayName("Deve retornar erro para raça hound-walker inexistente")
    @Description("Valida que a API responde com erro quando a raça hound-walker é informada e não está cadastrada.")
    void shouldReturnErrorForHoundWalkerBreed() {
        var response = get("/breed/hound-walker/images");
        AllureSupport.attachResponse("breed-images-hound-walker", response);

        assertErrorResponse(response);
    }

    /**
     * Valida a resposta de erro retornada pela API para raças inválidas ou inexistentes.
     *
     * @param response resposta HTTP recebida
     * @return nenhuma saída é produzida; o método falha o teste caso a resposta não siga o contrato de erro
     */
    private void assertErrorResponse(Response response) {
        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(response.jsonPath().getString("status")).isEqualTo("error");
        assertThat(response.jsonPath().getString("message")).isNotBlank();
    }
}
