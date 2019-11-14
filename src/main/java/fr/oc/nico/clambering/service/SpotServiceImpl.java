package fr.oc.nico.clambering.service;


import fr.oc.nico.clambering.DTO.*;
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

    private final LongueurService longueurService;

    private final VoieService voieService;

    private final SecteurService secteurService;

    @Autowired
    public SpotServiceImpl(SpotRepository spotRepository, PaysRepository paysRepository, RegionRepository regionRepository, LongueurService longueurService, VoieService voieService, SecteurService secteurService) {
        this.spotRepository = spotRepository;
        this.paysRepository = paysRepository;
        this.regionRepository = regionRepository;
        this.longueurService = longueurService;
        this.voieService = voieService;
        this.secteurService = secteurService;
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
        Spot addSpot = new Spot(null, newSpot.getSpotNom(), regionRepository.findByRegionLibelle(newSpot.getRegion()), newSpot.getSpotDescription(), newSpot.getAcces(), newSpot.getOrientation(), newSpot.getLongitude(), newSpot.getLatitude(), "logo.png");
        addSpot.addSecteur(tmpSecteur);
        return spotRepository.save(addSpot);
    }

    @Override
    public SpotFormRegistration getEmptySpot() {
        return new SpotFormRegistration();
    }

    @Override
    public SpotEditForm getSpotEditForm(Spot spot) {
        SpotEditForm spotEditForm = new SpotEditForm(spot.getSpotId(),spot.getRegion().getRegionLibelle(),spot.getSpotLibelle(), spot.getSpotDescription(), spot.getAcces(), spot.getOrientation(), spot.getLatitude(), spot.getLongitude(), spot.getImage());
        for (Secteur secteur: spot.getSecteurs()) {
                SecteurEditForm formSecteur = secteurService.mapSecteur(secteur);
                for (Voie voie: secteur.getVoies()) {
                        VoieEditForm formVoie = voieService.mapVoie(voie);
                        for (Longueur longueur : voie.getLongueurs()) {
                                formVoie.addLongueur(longueurService.mapLongueur(longueur));
                        }
                        formSecteur.addvoie(formVoie);
                }
                spotEditForm.addSecteur(formSecteur);
        }
        return spotEditForm;
    }

    @Override
    public void addNewSecteurToSpot(SpotEditForm spotEditForm) {
        spotEditForm.addSecteur(secteurService.getNewSecteur());
    }

    @Override
    public SpotEditForm addNewVoieToSpot(SpotEditForm spotEditForm, String addVoie) {
        spotEditForm.getSecteurs().get(Integer.parseInt(addVoie)).addvoie(voieService.getNewVoie());
        return spotEditForm;
    }

    @Override
    public SpotEditForm addNewLongueurToSpot(SpotEditForm spotEditForm, String addLongueur) {
        String[] indexes = addLongueur.split("_");
        spotEditForm.getSecteurs().get(Integer.parseInt(indexes[0])).getVoies().get(Integer.parseInt(indexes[1])).addLongueur(new LongueurFormRegistration());
        return spotEditForm;
    }

    @Override
    public SpotEditForm removeSecteurToSpot(SpotEditForm spotEditForm, String removeSecteur) {
        spotEditForm.getSecteurs().remove(Integer.parseInt(removeSecteur));
        return spotEditForm;
    }

    @Override
    public SpotEditForm removeVoieToSpot(SpotEditForm spotEditForm, String removeVoie) {
        String[] indexes = removeVoie.split("_");
        spotEditForm.getSecteurs().get(Integer.parseInt(indexes[0])).getVoies().remove(Integer.parseInt(indexes[1]));
        return spotEditForm;
    }

    @Override
    public SpotEditForm removeLongueurToSpot(SpotEditForm spotEditForm, String removeLongueur) {
        String[] indexes = removeLongueur.split("_");
        spotEditForm.getSecteurs().get(Integer.parseInt(indexes[0])).getVoies().get(Integer.parseInt(indexes[1])).getLongueurs().remove(Integer.parseInt(indexes[2]));
        return spotEditForm;
    }

    @Override
    public Spot updateSpot(SpotEditForm spotEditForm) {
        Spot updateSpot = new Spot(spotEditForm.getSpotId(), spotEditForm.getSpotNom(), regionRepository.findByRegionLibelle(spotEditForm.getRegion()), spotEditForm.getSpotDescription(), spotEditForm.getAcces(), spotEditForm.getOrientation(), spotEditForm.getLongitude(), spotEditForm.getLatitude(), spotEditForm.getImage());
        //updateSpot.setSpotId(spotId);
        for (SecteurEditForm secteurEditForm: spotEditForm.getSecteurs()) {
            Secteur secteur = secteurService.updateSecteur(secteurEditForm);
            for (VoieEditForm voieEditForm: secteurEditForm.getVoies()) {
                Voie voie = voieService.updateVoie(voieEditForm);
                for (LongueurFormRegistration longueurFormRegistration: voieEditForm.getLongueurs()) {
                    voie.addLongueur(longueurService.updateLongueur(longueurFormRegistration));
                }
                secteur.addVoie(voie);
            }
            updateSpot.addSecteur(secteur);
        }
        return spotRepository.save(updateSpot);
    }

}
