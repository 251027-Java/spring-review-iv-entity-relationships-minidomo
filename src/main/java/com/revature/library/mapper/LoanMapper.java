package com.revature.library.mapper;

import com.revature.library.dto.LoanDto;
import com.revature.library.model.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    LoanDto.Own toDto(Loan loan);
}
