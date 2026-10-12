package com.arnouddev89.webcar_api.controller;

import com.arnouddev89.webcar_api.dto.CarPostDTO;
import com.arnouddev89.webcar_api.message.KafkaProducerMessage;
import com.arnouddev89.webcar_api.service.CarPostStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarPostControllerTest {

    @Mock
    private CarPostStoreService carPostStoreService;

    @Mock
    private KafkaProducerMessage kafkaProducerMessage;

    @InjectMocks
    private CarPostController carPostController;

    private CarPostDTO carPostDTO;

    @BeforeEach
    void setUp() {
        carPostDTO = new CarPostDTO();
        // Ajuste conforme seu DTO real
        // carPostDTO.setModel("Golf GTI");
        // carPostDTO.setBrand("VW");
        // carPostDTO.setPrice(90000.0);
    }

    @Test
    @DisplayName("POST /api/car/post - Deve enviar mensagem para o Kafka e retornar 200 OK")
    void postCarforSale_shouldSendMessageAndReturnOk() {
        // Act
        ResponseEntity response = carPostController.postCarforSale(carPostDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(kafkaProducerMessage, times(1)).sendMessage(carPostDTO);
        verifyNoInteractions(carPostStoreService);
    }

    @Test
    @DisplayName("GET /api/car/posts - Deve retornar lista de carros com status 302 FOUND")
    void getCarSales_shouldReturnListAndFound() {
        // Arrange
        List<CarPostDTO> mockList = Arrays.asList(carPostDTO, new CarPostDTO());
        when(carPostStoreService.getCarForSales()).thenReturn(mockList);

        // Act
        ResponseEntity<List<CarPostDTO>> response = carPostController.getCarSales();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(carPostStoreService, times(1)).getCarForSales();
    }

    @Test
    @DisplayName("GET /api/car/posts - Deve retornar lista vazia")
    void getCarSales_shouldReturnEmptyList() {
        when(carPostStoreService.getCarForSales()).thenReturn(Collections.emptyList());

        ResponseEntity<List<CarPostDTO>> response = carPostController.getCarSales();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    @DisplayName("PUT /api/car/{id} - Deve alterar a venda e retornar 200 OK")
    void changeCarSale_shouldCallServiceAndReturnOk() {
        String id = "123";

        ResponseEntity response = carPostController.changeCarSale(carPostDTO, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(carPostStoreService, times(1)).changeCarForSale(carPostDTO, id);
    }

    @Test
    @DisplayName("DELETE /api/car/{id} - Deve remover a venda e retornar 200 OK")
    void deleteCarForSale_shouldCallServiceAndReturnOk() {
        String id = "123";

        ResponseEntity response = carPostController.deleteCarForSale(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(carPostStoreService, times(1)).removeCarForSale(id);
    }
}