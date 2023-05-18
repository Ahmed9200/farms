package com.example.authentication.repositories;

import com.example.authentication.models.ExperiencesYears;
import com.example.authentication.models.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperiencesYearsRepository extends JpaRepository<ExperiencesYears, Integer> {

}
