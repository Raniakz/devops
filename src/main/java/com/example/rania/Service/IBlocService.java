package com.example.rania.Service;

import com.example.rania.Entity.Bloc;
import java.util.List;

public interface IBlocService {

    Bloc create(Bloc bloc);

    Bloc update(Long id, Bloc bloc);

    void delete(Long id);

    List<Bloc> getAll();

    Bloc getById(Long id);

    Bloc affecterBlocAFoyer(Long idBloc, Long idFoyer);

    Bloc affecterChambresABloc(List<Long> numChambre, long idBloc);
}




