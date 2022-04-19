package com.shym.backend.repository;

import com.shym.backend.enumeration.CarCategory;
import com.shym.backend.model.Agency;
import com.shym.backend.model.RentalOffer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RentalOfferRepository extends CrudRepository<RentalOffer, String> {
    List<RentalOffer> findRentalOfferByAgency(Agency agency);

//    @Modifying
//    @Transactional
//    @Query("UPDATE RentalOffer r SET r.pricePerDay = (r.pricePerDay * :increaseRatio) WHERE r.agency = :agency AND r.car.category = :category")
//    void increasePricePerCategory(@Param("agency") Agency agency, @Param("category")  String category, @Param("increaseRatio")  Integer increaseRatio);
}
