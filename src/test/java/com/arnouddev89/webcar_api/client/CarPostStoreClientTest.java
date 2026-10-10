package com.arnouddev89.webcar_api.client;

import com.arnouddev89.webcar_api.dto.CarPostDTO;
import com.arnouddev89.webcar_api.dto.OwnerPostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarPostStoreClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CarPostStoreClient carPostStoreClient;

    private CarPostDTO carPostDTO;
    private OwnerPostDTO ownerPostDTO;

    @BeforeEach
    void setUp() {
        carPostDTO = new CarPostDTO();
        carPostDTO.setModel("Civic");

        ownerPostDTO = new OwnerPostDTO();
        ownerPostDTO.setName("Arnoud");
    }

    @Test
    void deveBuscarCarrosAVenda() {
        CarPostDTO[] array = new CarPostDTO[]{carPostDTO};
        ResponseEntity<CarPostDTO[]> response = new ResponseEntity<>(array, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(CarPostDTO[].class))).thenReturn(response);

        List<CarPostDTO> result = carPostStoreClient.carForSaleClient();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getModel()).isEqualTo("Civic");
        verify(restTemplate).getForEntity(contains("/sales/cars"), eq(CarPostDTO[].class));
    }

    @Test
    void deveCriarOwnerPost() {
        ResponseEntity<OwnerPostDTO> response = new ResponseEntity<>(ownerPostDTO, HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), any(OwnerPostDTO.class), eq(OwnerPostDTO.class))).thenReturn(response);

        carPostStoreClient.ownerPostsClient(ownerPostDTO);

        verify(restTemplate).postForEntity(contains("/user"), eq(ownerPostDTO), eq(OwnerPostDTO.class));
    }

    @Test
    void deveAlterarCarroAVenda() {
        doNothing().when(restTemplate).put(anyString(), any(CarPostDTO.class), eq(CarPostDTO.class));

        carPostStoreClient.changeCarForSaleClient(carPostDTO, "1");

        verify(restTemplate).put(contains("/user/car/1"), eq(carPostDTO), eq(CarPostDTO.class));
    }

    @Test
    void deveDeletarCarroAVenda() {
        doNothing().when(restTemplate).delete(anyString());

        carPostStoreClient.deleteCarForSaleClient("10");

        verify(restTemplate).delete(contains("/user/car/10"));
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumCarro() {
        CarPostDTO[] array = new CarPostDTO[]{};
        ResponseEntity<CarPostDTO[]> response = new ResponseEntity<>(array, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(CarPostDTO[].class))).thenReturn(response);

        List<CarPostDTO> result = carPostStoreClient.carForSaleClient();

        assertThat(result).isEmpty();
    }
}
