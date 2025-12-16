package com.example.rania.Mapper;

import com.example.rania.DTO.FoyerDTO;
import com.example.rania.Entity.Foyer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoyerMapper {
    FoyerDTO toDto(Foyer foyer);
    Foyer toEntity(FoyerDTO foyerDTO);
}
