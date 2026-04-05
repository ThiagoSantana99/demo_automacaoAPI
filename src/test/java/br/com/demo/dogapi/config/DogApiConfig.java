package br.com.demo.dogapi.config;

/**
 * Centraliza a configuração de acesso à Dog API utilizada pelos testes automatizados.
 * <p>
 * A classe resolve a URL base e o base path da API a partir de propriedades do sistema,
 * permitindo sobrescrita em diferentes ambientes sem alterar o código-fonte.
 *
 * @author Thiago Santana
 * @version 1.0
 */
public final class DogApiConfig {

    private static final String DEFAULT_BASE_URI = "https://dog.ceo";
    private static final String DEFAULT_BASE_PATH = "/api";

    /**
     * Impede a instanciação desta classe utilitária.
     */
    private DogApiConfig() {
    }

    /**
     * Obtém a URI base da Dog API.
     *
     * @return a URI base configurada ou o valor padrão {@code https://dog.ceo}
     */
    public static String baseUri() {
        return System.getProperty("dog.api.base-uri", DEFAULT_BASE_URI);
    }

    /**
     * Obtém o base path da Dog API.
     *
     * @return o base path configurado ou o valor padrão {@code /api}
     */
    public static String basePath() {
        return System.getProperty("dog.api.base-path", DEFAULT_BASE_PATH);
    }
}
