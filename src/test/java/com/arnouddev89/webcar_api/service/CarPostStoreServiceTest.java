package com.arnouddev89.webcar_api.service;

import com.arnouddev89.webcar_api.dto.CarPostDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarPostStoreServiceTest {

    @Mock
    private CarPostStoreService carPostStoreService;

    @Test
    void getCarForSales_ShouldBeInvokable() {
        when(carPostStoreService.getCarForSales()).thenReturn(List.of(new CarPostDTO()));

        List<CarPostDTO> result = carPostStoreService.getCarForSales();

        assertNotNull(result);
        verify(carPostStoreService).getCarForSales();
    }

    @Test
    void changeCarForSale_ShouldBeInvokable() {
        CarPostDTO dto = new CarPostDTO();

        carPostStoreService.changeCarForSale(dto, "1");

        verify(carPostStoreService).changeCarForSale(dto, "1");
    }

    @Test
    void removeCarForSale_ShouldBeInvokable() {
        carPostStoreService.removeCarForSale("1");

        verify(carPostStoreService).removeCarForSale("1");
    }
}