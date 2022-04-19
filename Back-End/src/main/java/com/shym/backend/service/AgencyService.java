package com.shym.backend.service;

import com.shym.backend.dto.CreateAgencyDto;
import com.shym.backend.dto.EditAgencyDto;
import com.shym.backend.dtoMappers.AgencyDtoMapper;
import com.shym.backend.exception.UserAlreadyExistsException;
import com.shym.backend.model.Agency;
import com.shym.backend.repository.AgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class AgencyService {
    private AgencyRepository agencyRepository;
    private FileService fileService;

    public Agency createAgency(CreateAgencyDto dto) {
        if(agencyRepository.existsByEmail(dto.email())) throw new UserAlreadyExistsException("Agency exits!");
        Agency agency = AgencyDtoMapper.createAgencyDtoMapper(dto);
        return agencyRepository.save(agency);
    }

    public Agency editAgency(EditAgencyDto dto, String email) {
        Agency agency = agencyRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Agency not found!")
        );
        agency.setDescription(dto.description());
        agency.setName(dto.name());
        return agencyRepository.save(agency);
    }

    public Agency addAgencyImage(MultipartFile image, String email) {
        Agency agency = agencyRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Agency with the email {0} was not found!", email))
        );
        try {
            fileService.uploadUserImage(agency, image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return agencyRepository.save(agency);
    }

    public Agency getAgencyWithEmail(String email) {
        return agencyRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Agency with the email {0} was not found!", email))
        );
    }
}