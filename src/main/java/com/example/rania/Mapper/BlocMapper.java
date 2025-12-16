package com.example.rania.Mapper;

import com.example.rania.DTO.BlocDTO;
import com.example.rania.Entity.Bloc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlocMapper {
    BlocDTO toDto(Bloc bloc);
    Bloc toEntity(BlocDTO blocDTO);
}