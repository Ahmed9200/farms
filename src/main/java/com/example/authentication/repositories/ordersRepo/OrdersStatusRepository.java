package com.example.authentication.repositories.ordersRepo;

import com.example.authentication.models.orders.Orders;
import com.example.authentication.models.orders.OrdersStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersStatusRepository extends JpaRepository<OrdersStatus, Integer> {


    @Query(value = "SELECT * " +
            " FROM orders_status " +
            " WHERE order_id = ?1 order by date asc ", nativeQuery = true)
    List<OrdersStatus> orderTimeLine(int orderId);


}
