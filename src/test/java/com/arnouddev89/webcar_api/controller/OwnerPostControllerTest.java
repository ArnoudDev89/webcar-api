package com.arnouddev89.webcar_api.controller;

import com.arnouddev89.webcar_api.dto.OwnerPostDTO;
import com.arnouddev89.webcar_api.service.OwnerPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OwnerPostControllerTest {

    @Mock
    private OwnerPostService ownerPostService;

    @InjectMocks
    private OwnerPostController ownerPostController;

    private OwnerPostDTO dto;

    @BeforeEach
    void setUp() {
        dto = new OwnerPostDTO();
    }

    @Test
    void createOwnerCar_ReturnsOk() {
        ResponseEntity response = ownerPostController.createOwnerCar(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(ownerPostService).createOwnerCar(dto);
    }
}