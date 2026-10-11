package com.arnouddev89.webcar_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest(properties = {
		"spring.kafka.bootstrap-servers=localhost:9092",
		"spring.kafka.producer.bootstrap-servers=localhost:9092",
		"spring.kafka.consumer.bootstrap-servers=localhost:9092"
})
class WebcarApiApplicationTests {

	@MockBean
	private KafkaTemplate<String, Object> kafkaTemplate;

	@MockBean
	private com.arnouddev89.webcar_api.message.KafkaProducerMessage kafkaProducerMessage;

	@Test
	void contextLoads() {
	}
}
