package com.example.rania.Service;

import com.example.rania.Entity.Bloc;
import com.example.rania.Repository.BlocRepository;
import com.example.rania.Repository.ChambreRepository;
import com.example.rania.Entity.Chambre;
import com.example.rania.Utils.Enum.TypeChambre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChambreService implements IChambreService {
    private final ChambreRepository chambreRepository;
    private final BlocRepository blocRepository;

    public ChambreService(ChambreRepository chambreRepository, BlocRepository blocRepository) {
        this.chambreRepository = chambreRepository;
        this.blocRepository = blocRepository;
    }

    @Override
    public Chambre create(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre update(Long id, Chambre chambre) {
        chambre.setIdChambre(id);
        return chambreRepository.save(chambre);
    }

    @Override
    public void delete(Long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public List<Chambre> getAll() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre getById(Long id) {
        return chambreRepository.findById(id).orElseThrow(() -> new RuntimeException("Chambre not found"));
    }

    @Override
    public Chambre affecterChambreABloc(Long idChambre, Long idBloc) {
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new RuntimeException("Chambre not found"));

        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc not found"));

        chambre.setBloc(bloc);
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        return chambreRepository.findByBlocFoyerUniversiteNomUniversite(nomUniversite);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.findByBlocIdBlocAndTypeC(idBloc, typeC);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        List<Chambre> chambres = chambreRepository.findByBlocFoyerUniversiteNomUniversiteAndTypeC(nomUniversite, type);
        return chambres.stream()
                .filter(c -> c.getReservations() == null ||
                        c.getReservations().isEmpty() ||
                        c.getReservations().stream().noneMatch(r -> r.getEstValide() != null && r.getEstValide()))
                .collect(java.util.stream.Collectors.toList());
    }
}




