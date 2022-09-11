package com.example.hw_institutionCatalog.repository;

import com.example.hw_institutionCatalog.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

    @Override
    Optional<Institution> findById(Integer integer);

    Optional<List<Institution>> findAllByOwnerId(Integer ownerId);
    boolean existsInstitutionByOwnerId(Integer ownerId);
    void deleteAllByOwnerId(Integer ownerId);
    void deleteOwnerIdById(Integer id);
}
