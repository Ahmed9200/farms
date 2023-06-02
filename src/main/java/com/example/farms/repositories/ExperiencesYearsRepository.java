package com.example.farms.repositories;

import com.example.farms.models.entities.ExperiencesYears;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperiencesYearsRepository extends JpaRepository<ExperiencesYears, Integer> {

    List<ExperiencesYears> findAllByYearLike(String year);

}
