package com.example.rania.Repository;

import com.example.rania.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    Optional<Reservation> findByEtudiantsCinAndEstValide(Long cin, Boolean estValide);
}
