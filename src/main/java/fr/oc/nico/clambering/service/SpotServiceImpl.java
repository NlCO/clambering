package fr.oc.nico.clambering.service;


import fr.oc.nico.clambering.model.Spot;
import fr.oc.nico.clambering.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service("SpotService")
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotRepository spotRepository;

    @Override
    public List<Spot> listeSpots() {
        return spotRepository.findAll();
    }

    @Override
    public Spot spotInfo(Integer spotId) {
        return spotRepository.findById(spotId)
                .orElseThrow(() -> new EntityNotFoundException("Le spot " + spotId + "n'exite pas"));
    }
}
