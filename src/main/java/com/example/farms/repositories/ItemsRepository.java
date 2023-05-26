package com.example.farms.repositories;

import com.example.farms.models.Items;
import org.apache.catalina.LifecycleState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {

    Page<Items> findAllByNameLike(String name, Pageable pageable);
    Page<Items> findAllBySaleGreaterThan(int number , Pageable pageable);
    Page<Items> findAllByNewItemsIsTrue(Pageable pageable);
    Page<Items> findAllByCategoryId(int catId,Pageable pageable);

}
