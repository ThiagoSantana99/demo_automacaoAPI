package br.com.demo.dogapi.model;

import java.util.List;

/**
 * Representa a resposta do endpoint {@code GET /breed/{breed}/images}.
 * <p>
 * O atributo {@code message} contém a lista de URLs das imagens da raça consultada.
 *
 * @param message lista de URLs das imagens retornadas pela API
 * @param status status funcional da resposta da API
 *
 * @author Thiago Santana
 * @version 1.0
 */
public record BreedImagesResponse(List<String> message, String status) {
}
