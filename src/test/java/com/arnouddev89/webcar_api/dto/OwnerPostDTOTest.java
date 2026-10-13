package com.arnouddev89.webcar_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerPostDTOTest {

    @Test
    void builder_ShouldBuildCorrectly() {
        OwnerPostDTO dto = OwnerPostDTO.builder()
                .name("Arnoud")
                .type("USER")
                .contactNumber("61999999999")
                .build();

        assertEquals("Arnoud", dto.getName());
        assertEquals("USER", dto.getType());
        assertEquals("61999999999", dto.getContactNumber());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyInstance() {
        OwnerPostDTO dto = new OwnerPostDTO();

        assertNotNull(dto);
        assertNull(dto.getName());
    }

    @Test
    void allArgsConstructor_ShouldCreateFullyPopulatedInstance() {
        OwnerPostDTO dto = new OwnerPostDTO("Arnoud", "USER", "61999999999");

        assertEquals("Arnoud", dto.getName());
        assertEquals("USER", dto.getType());
        assertEquals("61999999999", dto.getContactNumber());
    }

    @Test
    void settersAndGetters_ShouldWork() {
        OwnerPostDTO dto = new OwnerPostDTO();
        dto.setName("John");
        dto.setType("DEALER");
        dto.setContactNumber("61888888888");

        assertEquals("John", dto.getName());
        assertEquals("DEALER", dto.getType());
        assertEquals("61888888888", dto.getContactNumber());
    }

    @Test
    void equalsAndHashCode_ShouldBeConsistent() {
        OwnerPostDTO dto1 = OwnerPostDTO.builder().name("Arnoud").type("USER").build();
        OwnerPostDTO dto2 = OwnerPostDTO.builder().name("Arnoud").type("USER").build();
        OwnerPostDTO dto3 = OwnerPostDTO.builder().name("Other").type("DEALER").build();

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
    }

    @Test
    void toString_ShouldContainFields() {
        OwnerPostDTO dto = OwnerPostDTO.builder().name("Arnoud").build();

        String toString = dto.toString();

        assertTrue(toString.contains("Arnoud"));
    }
}