package com.example.authentication.repositories.configRepo;

import com.example.authentication.models.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Integer> {

    @Query(value = "SELECT * FROM config", nativeQuery = true)
    List<Map<String, String>> getAllConfigurations();
    

}
