package com.example.rania.Mapper;

import com.example.rania.DTO.EtudiantDTO;
import com.example.rania.Entity.Etudiant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EtudiantMapper {
    EtudiantDTO toDto(Etudiant etudiant);
    Etudiant toEntity(EtudiantDTO etudiantDTO);
}