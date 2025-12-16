package com.example.rania.Service;

import com.example.rania.Entity.Etudiant;
import java.util.List;

public interface IEtudiantService {
    Etudiant create(Etudiant etudiant);
    Etudiant update(Long id, Etudiant etudiant);
    void delete(Long id);
    List<Etudiant> getAll();
    Etudiant getById(Long id);
}

