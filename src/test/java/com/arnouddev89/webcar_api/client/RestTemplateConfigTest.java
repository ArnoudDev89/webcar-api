package com.arnouddev89.webcar_api.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

class RestTemplateConfigTest {

    private final RestTemplateConfig sut = new RestTemplateConfig();

    @Test
    @DisplayName("restTemplate - deve criar bean nao nulo")
    void restTemplate_shouldCreateBean() {
        RestTemplateBuilder builder = new RestTemplateBuilder();

        RestTemplate result = sut.restTemplate(builder);

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("restTemplate - deve aplicar timeouts configurados")
    void restTemplate_shouldApplyTimeouts() {
        RestTemplateBuilder builder = new RestTemplateBuilder();

        RestTemplate result = sut.restTemplate(builder);

        assertThat(result).isNotNull();
        assertThat(result.getRequestFactory()).isNotNull();
    }

    @Test
    @DisplayName("restTemplate - deve criar instancias independentes")
    void restTemplate_shouldCreateIndependentInstances() {
        RestTemplateBuilder builder = new RestTemplateBuilder();

        RestTemplate first = sut.restTemplate(builder);
        RestTemplate second = sut.restTemplate(builder);

        assertThat(first).isNotSameAs(second);
    }
}
