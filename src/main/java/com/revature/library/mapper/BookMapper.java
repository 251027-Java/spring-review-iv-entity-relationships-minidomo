package com.revature.library.mapper;

import com.revature.library.dto.BookDto;
import com.revature.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "available", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "loans", ignore = true)
    Book toEntity(BookDto.Creation dto);

    BookDto.Own toDto(Book book);
}
