package com.arnouddev89.webcar_api.message;

import com.arnouddev89.webcar_api.dto.CarPostDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class KafkaProducerConfigurationTest {

    private final KafkaProducerConfiguration configuration = new KafkaProducerConfiguration();

    @Test
    void userProducerFactory_ShouldCreateFactoryWithCorrectConfigs() {
        ReflectionTestUtils.setField(configuration, "bootstrapServer", "localhost:9092");

        ProducerFactory<String, CarPostDTO> factory = configuration.userProducerFactory();

        assertNotNull(factory);
        Map<String, Object> configs = factory.getConfigurationProperties();

        assertEquals("localhost:9092", configs.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertEquals(StringSerializer.class.getName(), configs.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
        assertEquals(JsonSerializer.class.getName(), configs.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
        assertEquals(false, configs.get(JsonSerializer.ADD_TYPE_INFO_HEADERS));
    }

    @Test
    void userKafkaTemplate_ShouldCreateTemplate() {
        ReflectionTestUtils.setField(configuration, "bootstrapServer", "localhost:9092");

        KafkaTemplate<String, CarPostDTO> template = configuration.userKafkaTemplate();

        assertNotNull(template);
        assertNotNull(template.getProducerFactory());
    }
}