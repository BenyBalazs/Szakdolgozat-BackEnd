package com.benyovszki.szakdolgozat.dto.converter;

public interface DtoToEntityConverter<source, destination> {

    destination toEntity(source dto);

    source toDto(destination dest);
}
