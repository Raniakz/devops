package com.example.rania.Mapper;

import com.example.rania.DTO.ChambreDTO;
import com.example.rania.Entity.Chambre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChambreMapper {
    ChambreDTO toDto(Chambre chambre);
    Chambre toEntity(ChambreDTO chambreDTO);
}
