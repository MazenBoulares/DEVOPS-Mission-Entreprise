package tn.esprit.spring.RestControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Repositories.BlocRepository;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bloc")
@RequiredArgsConstructor
public class BlocRestController {

    private final BlocRepository blocRepository;
    private final ChambreRepository chambreRepository;
    private final FoyerRepository foyerRepository;

    @PostMapping("/addOrUpdate")
    public Bloc addOrUpdate(@RequestBody Bloc b) {
        List<Chambre> chambres = b.getChambres();
        b = blocRepository.save(b);
        for (Chambre chambre : chambres) {
            chambre.setBloc(b);
            chambreRepository.save(chambre);
        }
        return b;
    }

    @GetMapping("/findAll")
    public List<Bloc> findAll() {
        return blocRepository.findAll();
    }

    @GetMapping("/findById")
    public Bloc findById(@RequestParam long id) {
        return blocRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody Bloc b) {
        chambreRepository.deleteAll(b.getChambres());
        blocRepository.delete(b);
        return "Deleted";
    }

    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam long id) {
        Bloc b = blocRepository.findById(id).orElse(null);
        if (b != null) {
            chambreRepository.deleteAll(b.getChambres());
            blocRepository.delete(b);
            return "Deleted";
        }
        return "Not found";
    }

    @PutMapping("/affecterChambresABloc")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre, @RequestParam String nomBloc) {
        Bloc b = blocRepository.findByNomBloc(nomBloc);
        if (b == null) return null;
        for (Long num : numChambre) {
            Chambre c = chambreRepository.findByNumeroChambre(num);
            if (c != null) {
                c.setBloc(b);
                chambreRepository.save(c);
            }
        }
        return b;
    }

    @PutMapping("/affecterBlocAFoyer")
    public Bloc affecterBlocAFoyer(@RequestParam String nomBloc, @RequestParam String nomFoyer) {
        Bloc b = blocRepository.findByNomBloc(nomBloc);
        Foyer f = foyerRepository.findByNomFoyer(nomFoyer);
        if (b == null || f == null) return null;
        b.setFoyer(f);
        return blocRepository.save(b);
    }

    @PutMapping("/affecterBlocAFoyer2/{nomFoyer}/{nomBloc}")
    public Bloc affecterBlocAFoyer2(@PathVariable String nomBloc, @PathVariable String nomFoyer) {
        return affecterBlocAFoyer(nomBloc, nomFoyer);
    }

    @PostMapping("/ajouterBlocEtSesChambres")
    public Bloc ajouterBlocEtSesChambres(@RequestBody Bloc b) {
        List<Chambre> chambres = b.getChambres();
        b = blocRepository.save(b);
        for (Chambre c : chambres) {
            c.setBloc(b);
            chambreRepository.save(c);
        }
        return b;
    }

    @PostMapping("/ajouterBlocEtAffecterAFoyer/{nomF}")
    public Bloc ajouterBlocEtAffecterAFoyer(@RequestBody Bloc b, @PathVariable String nomF) {
        Foyer f = foyerRepository.findByNomFoyer(nomF);
        if (f == null) return null;
        b.setFoyer(f);
        b = blocRepository.save(b);
        for (Chambre c : b.getChambres()) {
            c.setBloc(b);
            chambreRepository.save(c);
        }
        return b;
    }
}
