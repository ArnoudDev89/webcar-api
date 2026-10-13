package com.arnouddev89.webcar_api.service;

import com.arnouddev89.webcar_api.client.CarPostStoreClient;
import com.arnouddev89.webcar_api.dto.OwnerPostDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OwnerPostServiceImplTest {

    @Mock
    private CarPostStoreClient carPostStoreClient;

    @InjectMocks
    private OwnerPostServiceImpl ownerPostService;

    @Test
    void createOwnerCar_ShouldDelegateToClient() {
        OwnerPostDTO dto = OwnerPostDTO.builder()
                .name("Arnoud")
                .type("USER")
                .contactNumber("61999999999")
                .build();

        ownerPostService.createOwnerCar(dto);

        verify(carPostStoreClient).ownerPostsClient(dto);
    }
}