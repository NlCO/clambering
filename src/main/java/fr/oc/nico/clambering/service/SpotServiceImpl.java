package fr.oc.nico.clambering.service;


import fr.oc.nico.clambering.DTO.SpotFormRegistration;
import fr.oc.nico.clambering.model.*;
import fr.oc.nico.clambering.repository.PaysRepository;
import fr.oc.nico.clambering.repository.RegionRepository;
import fr.oc.nico.clambering.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service("SpotService")
public class SpotServiceImpl implements SpotService {

    private final SpotRepository spotRepository;

    private final PaysRepository paysRepository;

    private final RegionRepository regionRepository;

    @Autowired
    public SpotServiceImpl(SpotRepository spotRepository, PaysRepository paysRepository, RegionRepository regionRepository) {
        this.spotRepository = spotRepository;
        this.paysRepository = paysRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Spot> listeSpots() {
        return spotRepository.findAll();
    }

    @Override
    public Spot spotInfo(Integer spotId) {
        return spotRepository.findById(spotId)
                .orElseThrow(() -> new EntityNotFoundException("Le spot " + spotId + " n'exite pas"));
    }

    @Override
    public List<Spot> filterSpots(SpotFormCriterias criterias) {
        return spotRepository.multiCriteriaSpotSearch(criterias);
    }


    @Override
    public SpotFormInfo getSpotFormInfo() {
        List<String> orientations = Arrays.asList("Nord", "Nord-Est", "Est", "Sud-Est", "Sud", "Sud-Ouest", "Ouest", "Nord-Ouest");
        List<String> cotations = Arrays.asList("3", "4", "5a", "5b", "5c", "6a", "6b", "6c", "7a", "7b", "7c", "8a", "8b", "8c", "9a", "9b", "9c");

        return new SpotFormInfo(paysRepository.findAll(), regionRepository.findAll(), orientations, cotations);
    }

    @Override
    public Spot ajouterSpot(SpotFormRegistration newSpot) {
        Longueur tmpLongueur = new Longueur(newSpot.getLongueurNom(), newSpot.getHauteur(), newSpot.getCotation(), newSpot.getDegaine());
        Voie tmpVoie = new Voie(newSpot.getVoieNom());
        tmpVoie.addLongueur(tmpLongueur);
        Secteur tmpSecteur = new Secteur(newSpot.getSecteurNom(), newSpot.getSecteurDescription());
        tmpSecteur.addVoie(tmpVoie);
        Spot addSpot = new Spot(newSpot.getSpotNom(), regionRepository.findByNom(newSpot.getRegion()), newSpot.getSpotDescription(), newSpot.getAcces(), newSpot.getOrientation(), newSpot.getLongitude(), newSpot.getLatitude());
        addSpot.addSecteur(tmpSecteur);
        return spotRepository.save(addSpot);
    }

    @Override
    public SpotFormRegistration getEmptySpot() {
        return new SpotFormRegistration();
    }

}
