package bme.webapp.freelancer.repository;

import bme.webapp.freelancer.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
