package com.shym.backend.service;

import com.shym.backend.dto.CreateAgencyDto;
import com.shym.backend.dtoMappers.AgencyDtoMapper;
import com.shym.backend.exception.UserAlreadyExistsException;
import com.shym.backend.model.Agency;
import com.shym.backend.repository.AgencyRepository;
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
}