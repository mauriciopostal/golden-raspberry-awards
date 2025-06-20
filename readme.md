# 🎬 Golden Raspberry Awards API

Este projeto é uma API REST desenvolvida com **Java 21**, **Spring Boot** e **Gradle**, que processa os dados do prêmio Golden Raspberry Awards (Pior Filme) e fornece informações sobre os produtores com **maior e menor intervalo entre vitórias**.

---

## ✅ Tecnologias utilizadas

- Java 21
- Spring Boot
- Gradle
- H2 (banco em memória)
- SpringDoc OpenAPI (Swagger)

---

## 🚀 Como executar o projeto

### 1. Clonar o repositório

```bash
git clone git@github.com:mauriciopostal/golden-raspberry-awards.git
cd golden-raspberry-awards
```

### 2. Executar com Gradle

```bash
./gradlew bootRun
```

A aplicação será iniciada em:

```
http://localhost:8080
```

---

## 📂 Endpoints disponíveis

### `GET /producers/intervals`

Retorna os produtores com:

- Maior intervalo entre vitórias
- Menor intervalo entre vitórias

#### Exemplo de resposta:

```json
{
  "min": [
    {
      "producer": "Producer 1",
      "interval": 1,
      "previousWin": 2008,
      "followingWin": 2009
    },
    {
      "producer": "Producer 2",
      "interval": 1,
      "previousWin": 2018,
      "followingWin": 2019
    }
  ],
  "max": [
    {
      "producer": "Producer 1",
      "interval": 99,
      "previousWin": 1900,
      "followingWin": 1999
    },
    {
      "producer": "Producer 2",
      "interval": 99,
      "previousWin": 2000,
      "followingWin": 2099
    }
  ]
}
```

---

## 📘 Documentação da API (Swagger)

Documentação interativa disponível em:

```
http://localhost:8080/swagger-ui.html
```

---

## 📦 Estrutura do projeto

- Os dados dos vencedores são carregados a partir de um arquivo `.csv` na inicialização.
- O banco de dados é **H2 em memória** (não persistente).

---
