package com.example.farms.repositories;

import com.example.farms.models.entities.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnersRepository extends JpaRepository<Partners, Integer> {

    List<Partners> findAllByNameLike(String name);
}
