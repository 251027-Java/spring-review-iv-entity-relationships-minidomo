package com.revature.library.mapper;

import com.revature.library.dto.PatronDto;
import com.revature.library.model.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatronMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "memberSince", ignore = true)
    @Mapping(target = "loans", ignore = true)
    Patron toEntity(PatronDto.Creation dto);

    PatronDto.Own toDto(Patron patron);
}
