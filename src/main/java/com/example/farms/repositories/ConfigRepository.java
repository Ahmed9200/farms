package com.example.farms.repositories;

import com.example.farms.models.entities.Categories;
import com.example.farms.models.entities.Configs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ConfigRepository extends JpaRepository<Configs, Integer> {

    Optional<Configs> findByName(String name);


    @Modifying
    @Transactional
    @Query(value = "UPDATE config SET value = ?2 WHERE name=?1 ", nativeQuery = true)
    void updateValue(String name, int value);

}
