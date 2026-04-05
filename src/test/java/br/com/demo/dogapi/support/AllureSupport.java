package br.com.demo.dogapi.support;

import io.qameta.allure.Allure;
import io.restassured.response.Response;

/**
 * Contém utilitários para anexar evidências dos testes ao relatório Allure.
 *
 * @author Thiago Santana
 * @version 1.0
 */
public final class AllureSupport {

    /**
     * Impede a instanciação desta classe utilitária.
     */
    private AllureSupport() {
    }

    /**
     * Anexa a request HTTP enviada ao Allure.
     *
     * @param title título base usado para identificar o anexo no relatório
     * @param method método HTTP executado
     * @param uri URI chamada pelo teste
     * @param accept tipo de conteúdo aceito pela request
     */
    public static void attachRequest(String title, String method, String uri, String accept) {
        var request = """
                method: %s
                uri: %s
                accept: %s
                """.formatted(method, uri, accept);
        Allure.addAttachment(title + " - request", request);
    }

    /**
     * Anexa o status e o corpo da resposta HTTP ao Allure.
     *
     * @param title título base usado para identificar os anexos no relatório
     * @param response resposta HTTP retornada pela chamada da API
     */
    public static void attachResponse(String title, Response response) {
        Allure.addAttachment(title + " - status", String.valueOf(response.statusCode()));
        Allure.addAttachment(title + " - body", response.asPrettyString());
    }
}
