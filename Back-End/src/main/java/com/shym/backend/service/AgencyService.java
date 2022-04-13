package com.shym.backend.service;

import com.shym.backend.dto.CreateAgencyDto;
import com.shym.backend.dtoMappers.AgencyDtoMapper;
import com.shym.backend.exception.UserAlreadyExistsException;
import com.shym.backend.model.Agency;
import com.shym.backend.repository.AgencyRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {
    AgencyRepository agencyRepository;
    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public Agency createAgency(CreateAgencyDto dto) {
        if(agencyRepository.existsByEmail(dto.email())) throw new UserAlreadyExistsException("Agency exits!");
        Agency agency = AgencyDtoMapper.createAgencyDtoMapper(dto);
        return agencyRepository.save(agency);
    }

    public Agency getAgencyWithEmail(String email) {
        return agencyRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Agency with the email {0} was not found!", email))
        );
    }
}