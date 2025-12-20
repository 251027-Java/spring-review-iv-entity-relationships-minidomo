package com.revature.library.service;

import com.revature.library.dto.PatronDto;
import com.revature.library.exception.PatronNotFoundException;
import com.revature.library.mapper.PatronMapper;
import com.revature.library.model.Patron;
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

    public Patron createPatron(PatronDto.Creation dto) {
        return patronRepository.save(patronMapper.toEntity(dto));
    }

    public Patron findById(Long patronId) {
        return patronRepository.findById(patronId).orElseThrow(() -> new PatronNotFoundException("Patron with id " + patronId + " not found"));
    }

    public List<Patron> findAll() {
        return patronRepository.findAll();
    }
}
