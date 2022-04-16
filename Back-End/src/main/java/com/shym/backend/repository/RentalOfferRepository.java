package com.shym.backend.repository;

import com.shym.backend.model.Agency;
import com.shym.backend.model.RentalOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalOfferRepository extends JpaRepository<RentalOffer, String> {
    List<RentalOffer> findRentalOfferByAgency(Agency agency);
}
