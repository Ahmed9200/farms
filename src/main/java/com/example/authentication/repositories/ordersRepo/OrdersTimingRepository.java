package com.example.authentication.repositories.ordersRepo;

import com.example.authentication.models.orders.OrdersStatus;
import com.example.authentication.models.orders.OrdersTiming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrdersTimingRepository extends JpaRepository<OrdersTiming, Integer> {

    List<OrdersTiming> findAllByDayLike(String day);

    @Modifying
    @Transactional
    @Query(value = "DELETE from orders_timing ot " +
            " WHERE ot.day like ?1 ;", nativeQuery = true)
    void deleteByDay(String day);
}
