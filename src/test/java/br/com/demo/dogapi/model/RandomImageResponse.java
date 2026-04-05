package br.com.demo.dogapi.model;

/**
 * Representa a resposta do endpoint {@code GET /breeds/image/random}.
 * <p>
 * O atributo {@code message} contém a URL de uma imagem aleatória de cão.
 *
 * @param message URL da imagem aleatória retornada pela API
 * @param status status funcional da resposta da API
 *
 * @author Thiago Santana
 * @version 1.0
 */
public record RandomImageResponse(String message, String status) {
}
