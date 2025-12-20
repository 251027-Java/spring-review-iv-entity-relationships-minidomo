package com.revature.library.service;

import com.revature.library.dto.PatronDto;
import com.revature.library.exception.PatronNotFoundException;
import com.revature.library.mapper.PatronMapper;
import com.revature.library.repository.PatronRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {
    private final PatronRepository patronRepository;
    private final PatronMapper patronMapper;

    public PatronService(PatronRepository patronRepository, PatronMapper patronMapper) {
        this.patronRepository = patronRepository;
        this.patronMapper = patronMapper;
    }

    public PatronDto.Own createPatron(PatronDto.Creation dto) {
        return patronMapper.toDto(patronRepository.save(patronMapper.toEntity(dto)));
    }

    public PatronDto.Own findById(Long patronId) {
        return patronRepository.findById(patronId).map(patronMapper::toDto).orElseThrow(() -> new PatronNotFoundException("Patron with id " + patronId + " not found"));
    }

    public List<PatronDto.Own> findAll() {
        return patronRepository.findAll().stream().map(patronMapper::toDto).toList();
    }
}
