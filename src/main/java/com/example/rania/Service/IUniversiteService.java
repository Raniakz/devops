package com.example.rania.Service;
import com.example.rania.Entity.Universite;
import java.util.List;

public interface IUniversiteService {
    Universite create(Universite universite);
    Universite update(Long id, Universite universite);
    void delete(Long id);
    List<Universite> getAll();
    Universite getById(Long id);
    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite(long idUniversite);
}
