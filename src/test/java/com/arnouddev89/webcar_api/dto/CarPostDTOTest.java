package com.arnouddev89.webcar_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarPostDTOTest {

    @Test
    void builder_ShouldBuildCorrectly() {
        CarPostDTO dto = CarPostDTO.builder()
                .model("Golf")
                .brand("VW")
                .price(90000.0)
                .description("GTI")
                .engineVersion("2.0")
                .city("Brasilia")
                .createdDate("2024-01-01")
                .ownerId(1L)
                .ownerName("Arnoud")
                .ownerType("USER")
                .contact("61999999999")
                .build();

        assertEquals("Golf", dto.getModel());
        assertEquals("VW", dto.getBrand());
        assertEquals(90000.0, dto.getPrice());
        assertEquals("GTI", dto.getDescription());
        assertEquals("2.0", dto.getEngineVersion());
        assertEquals("Brasilia", dto.getCity());
        assertEquals("2024-01-01", dto.getCreatedDate());
        assertEquals(1L, dto.getOwnerId());
        assertEquals("Arnoud", dto.getOwnerName());
        assertEquals("USER", dto.getOwnerType());
        assertEquals("61999999999", dto.getContact());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyInstance() {
        CarPostDTO dto = new CarPostDTO();

        assertNotNull(dto);
        assertNull(dto.getModel());
    }

    @Test
    void allArgsConstructor_ShouldCreateFullyPopulatedInstance() {
        CarPostDTO dto = new CarPostDTO(
                "Golf", "VW", 90000.0, "GTI", "2.0",
                "Brasilia", "2024-01-01", 1L, "Arnoud", "USER", "61999999999"
        );

        assertEquals("Golf", dto.getModel());
        assertEquals(1L, dto.getOwnerId());
    }

    @Test
    void settersAndGetters_ShouldWork() {
        CarPostDTO dto = new CarPostDTO();
        dto.setModel("Civic");
        dto.setBrand("Honda");
        dto.setPrice(100000.0);

        assertEquals("Civic", dto.getModel());
        assertEquals("Honda", dto.getBrand());
        assertEquals(100000.0, dto.getPrice());
    }

    @Test
    void equalsAndHashCode_ShouldBeConsistent() {
        CarPostDTO dto1 = CarPostDTO.builder().model("Golf").brand("VW").build();
        CarPostDTO dto2 = CarPostDTO.builder().model("Golf").brand("VW").build();
        CarPostDTO dto3 = CarPostDTO.builder().model("Civic").brand("Honda").build();

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
    }

    @Test
    void toString_ShouldContainFields() {
        CarPostDTO dto = CarPostDTO.builder().model("Golf").brand("VW").build();

        String toString = dto.toString();

        assertTrue(toString.contains("Golf"));
        assertTrue(toString.contains("VW"));
    }
}