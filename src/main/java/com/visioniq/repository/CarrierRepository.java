package com.visioniq.repository;

import com.visioniq.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {

    List<Carrier> findByActiveStatusTrue();

    List<Carrier> findByCategoryInAndActiveStatusTrue(List<String> categories);
}

