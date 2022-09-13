package com.example.hw_institutionCatalog.repository;

import com.example.hw_institutionCatalog.entity.Institution;
import com.example.hw_institutionCatalog.repository.data.InstitutionSmall;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

    @Override
    Optional<Institution> findById(Integer integer);

    Optional<List<Institution>> findAllByOwnerId(Integer ownerId);

    @Modifying
    @Query("update Institution i set i.ownerId = :newOwnerId where i.ownerId = :oldOwnerId")
    void updateUserSetStatusForName(@Param("newOwnerId") Integer newOwnerId, @Param("oldOwnerId") Integer oldOwnerId);

    @Query(value = "select i.id, max(i.name) as name, avg(r.rating) as average from Institution as i \n" +
            "join Review as r on i.id = r.institution_id \n" +
            "group by i.id", nativeQuery = true)
    List<InstitutionSmall> findSmallInstitutions(Pageable pageable);
}

