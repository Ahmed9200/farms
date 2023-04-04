package com.example.authentication.repositories.ordersRepo;

import com.example.authentication.models.orders.OrdersStatus;
import com.example.authentication.models.orders.OrdersTiming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersTimingRepository extends JpaRepository<OrdersTiming, Integer> {


    @Query(value = "SELECT * " +
            " FROM orders_timing " +
            " WHERE order_id = ?1 order by date asc ", nativeQuery = true)
    List<OrdersStatus> orderTimeLine(int orderId);


}
