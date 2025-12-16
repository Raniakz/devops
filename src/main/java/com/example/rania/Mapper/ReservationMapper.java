package com.example.rania.Mapper;

import com.example.rania.DTO.ReservationDTO;
import com.example.rania.Entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation toEntity(ReservationDTO dto);

    ReservationDTO toDto(Reservation reservation);
}