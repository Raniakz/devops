package com.example.rania.Mapper;

import com.example.rania.DTO.UniversiteDTO;
import com.example.rania.Entity.Universite;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UniversiteMapper {
    UniversiteDTO toDto(Universite universite);
    Universite toEntity(UniversiteDTO universiteDTO);
}