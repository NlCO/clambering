package fr.oc.nico.clambering.repository;

import fr.oc.nico.clambering.DTO.SpotFormCriterias;
import fr.oc.nico.clambering.model.Spot;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SpotCustomRepositoryImpl implements SpotCustomRepository {

    private EntityManager em;

    public SpotCustomRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Spot> multiCriteriaSpotSearch(SpotFormCriterias criterias) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Spot> query = builder.createQuery(Spot.class);

        Root<Spot> spotRoot = query.from(Spot.class);

        List<Predicate> predicates = new ArrayList<>();

        if (!criterias.getPays().equals("")) {
            predicates.add(builder.equal(spotRoot.get("region").get("pays").get("paysLibelle"), criterias.getPays()));
        }

        if (!criterias.getRegion().equals("")) {
            predicates.add(builder.equal(spotRoot.get("region").get("regionLibelle"), criterias.getRegion()));
        }

        if (!criterias.getOrientation().equals("")) {
            predicates.add(builder.equal(spotRoot.get("orientation"), criterias.getOrientation()));
        }

        if (!criterias.getCotationMin().equals("")) {
            predicates.add(builder.greaterThanOrEqualTo(spotRoot.join("secteurs").join("voies").join("longueurs").get("cotation"), criterias.getCotationMin()));
        }

        if (!criterias.getCotationMax().equals("")) {
            predicates.add(builder.lessThanOrEqualTo(spotRoot.join("secteurs").join("voies").join("longueurs").get("cotation"), criterias.getCotationMax()));
        }

        if (criterias.getMultiSecteurs()) {
            predicates.add(builder.gt(builder.size(spotRoot.get("secteurs")), 1));
        }

        if (criterias.getTagOfficiel().isPresent()) {
            if (criterias.getTagOfficiel().orElse(false)) {
                predicates.add(builder.isTrue((spotRoot.get("tagAmiEscalade"))));
            } else {
                predicates.add(builder.isFalse((spotRoot.get("tagAmiEscalade"))));
            }
        }

        query.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(query.distinct(true)).getResultList();
    }
}
