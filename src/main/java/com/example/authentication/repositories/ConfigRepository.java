package com.example.authentication.repositories;

import com.example.authentication.models.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ConfigRepository extends JpaRepository<Config, Integer> {

    @Query(value = "SELECT * FROM config", nativeQuery = true)
    List<Map<String, String>> getAllConfigurations();
    

}
