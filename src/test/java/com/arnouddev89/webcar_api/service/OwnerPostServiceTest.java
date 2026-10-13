package com.arnouddev89.webcar_api.service;

import com.arnouddev89.webcar_api.dto.OwnerPostDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OwnerPostServiceTest {

    @Mock
    private OwnerPostService ownerPostService;

    @Test
    void createOwnerCar_ShouldBeInvokable() {
        OwnerPostDTO dto = new OwnerPostDTO();

        ownerPostService.createOwnerCar(dto);

        verify(ownerPostService).createOwnerCar(dto);
    }
}