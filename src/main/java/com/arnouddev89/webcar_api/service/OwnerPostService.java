package com.arnouddev89.webcar_api.service;


import com.arnouddev89.webcar_api.dto.OwnerPostDTO;
import org.springframework.stereotype.Service;

@Service
public interface OwnerPostService {

    void createOwnerCar(OwnerPostDTO ownerPostDTO);
}
