package com.arnouddev89.webcar_api.message;

import com.arnouddev89.webcar_api.dto.CarPostDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class KafkaProducerMessageTest {

    @Mock
    private KafkaTemplate<String, CarPostDTO> kafkaTemplate;

    @InjectMocks
    private KafkaProducerMessage kafkaProducerMessage;

    @Test
    void sendMessage_ShouldSendToCorrectTopic() {
        CarPostDTO dto = new CarPostDTO();
        dto.setModel("Golf");

        kafkaProducerMessage.sendMessage(dto);

        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<CarPostDTO> dtoCaptor = ArgumentCaptor.forClass(CarPostDTO.class);

        verify(kafkaTemplate, times(1)).send(topicCaptor.capture(), dtoCaptor.capture());

        assertEquals("car-post-topic", topicCaptor.getValue());
        assertEquals(dto, dtoCaptor.getValue());
    }
}