# Dog API automated tests

Projeto de testes automatizados para a Dog API https://dog.ceo/dog-api/documentation. A aplicação permitirá que os usuários visualizem imagens de diferentes raças de cães, bem como aprendam sobre as raças disponíveis.

Projeto utiliza Java 17, Maven, RestAssured e Allure.

## O que cobre

- `GET /breeds/list/all`
- `GET /breed/{breed}/images`
- `GET /breeds/image/random`

Os testes validam status HTTP, formato do payload e consistência básica dos dados retornados. O relatório Allure inclui evidências da resposta de cada chamada.

## Cenários Mapeados

### `GET /breeds/list/all`

- Valida se a API retorna o catálogo completo de raças com status `200`.
- Confirma que o payload possui `status = success`.
- Verifica se o mapa de raças não vem vazio e contém dados conhecidos.

### `GET /breed/{breed}/images`

- Cenário de raça conhecida (`hound`): confirma retorno de imagens com URLs válidas.
- Cenário de formato inválido (`hound--walker`): valida que a API rejeita o valor e responde com erro.
- Cenário de raça inexistente (`papagaio`): valida que a API retorna erro para um valor não suportado.
- Cenário com raça `hound-walker`: valida que a API retorna erro porque a raça não existe no catálogo da Dog API.

### `GET /breeds/image/random`

- Valida se a API retorna uma imagem aleatória com status `200`.
- Confirma que o payload retorna `status = success`.
- Verifica se a URL da imagem é válida.

## Pré-requisitos

- Java 17
- Maven 3.9+
- Conexão com a internet para acessar a Dog API

## Executar localmente

```bash
mvn clean verify
```

Esse comando executa os testes e gera o relatório Allure via plugin Maven em `target/site/allure-maven`.

### Gerar relatório Allure separadamente

Se quiser executar apenas a geração do relatório após os testes:

```bash
mvn allure:report
```

## Execução via GitHub Actions

O workflow em `.github/workflows/ci.yml` executa:

1. `mvn clean test`
2. Instala o Allure CLI via `npm`
3. Gera o relatório Allure em `target/allure-report`
4. Publica o relatório no GitHub Pages em `https://thiagosantana99.github.io/demo_automacaoAPI/reports/`

## Publicação

O conteúdo publicado em GitHub Pages fica disponível em:

- `https://thiagosantana99.github.io/demo_automacaoAPI/reports/`

## Estrutura

- `src/test/java/.../tests` contém os testes da API
- `src/test/java/.../model` contém os mapeamentos das respostas
- `src/test/java/.../support` centraliza configuração e anexos do Allure
