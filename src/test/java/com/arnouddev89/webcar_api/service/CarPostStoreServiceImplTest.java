package com.arnouddev89.webcar_api.service;

import com.arnouddev89.webcar_api.client.CarPostStoreClient;
import com.arnouddev89.webcar_api.dto.CarPostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarPostStoreServiceImplTest {

    @Mock
    private CarPostStoreClient carPostStoreClient;

    @InjectMocks
    private CarPostStoreServiceImpl carPostStoreService;

    private CarPostDTO dto;

    @BeforeEach
    void setUp() {
        dto = new CarPostDTO();
    }

    @Test
    void getCarForSales_ShouldReturnFromClient() {
        List<CarPostDTO> expected = List.of(dto);
        when(carPostStoreClient.carForSaleClient()).thenReturn(expected);

        List<CarPostDTO> result = carPostStoreService.getCarForSales();

        assertEquals(expected, result);
        verify(carPostStoreClient).carForSaleClient();
    }

    @Test
    void changeCarForSale_ShouldDelegateToClient() {
        String id = "1";

        carPostStoreService.changeCarForSale(dto, id);

        verify(carPostStoreClient).changeCarForSaleClient(dto, id);
    }

    @Test
    void removeCarForSale_ShouldDelegateToClient() {
        String id = "1";

        carPostStoreService.removeCarForSale(id);

        verify(carPostStoreClient).deleteCarForSaleClient(id);
    }
}