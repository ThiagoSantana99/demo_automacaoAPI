package br.com.demo.dogapi.model;

import java.util.List;
import java.util.Map;

/**
 * Representa a resposta do endpoint {@code GET /breeds/list/all}.
 * <p>
 * O atributo {@code message} contém o catálogo de raças e sub-raças disponíveis.
 *
 * @param message mapa com a raça principal e suas sub-raças
 * @param status status funcional da resposta da API
 *
 * @author Thiago Santana
 * @version 1.0
 */
public record BreedsListResponse(Map<String, List<String>> message, String status) {
}
