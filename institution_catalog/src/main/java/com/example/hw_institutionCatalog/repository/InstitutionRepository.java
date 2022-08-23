package com.example.hw_institutionCatalog.repository;

import com.example.hw_institutionCatalog.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

}
