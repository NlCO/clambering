package fr.oc.nico.clambering.service;

import fr.oc.nico.clambering.model.Spot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SpotService {
    List<Spot> listeSpots();

    Spot spotInfo(Integer spotId);
}
