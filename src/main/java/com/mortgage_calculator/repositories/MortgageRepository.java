package com.mortgage_calculator.repositories;

import com.mortgage_calculator.entities.MortgageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MortgageRepository extends JpaRepository<MortgageEntity, UUID> {
}
