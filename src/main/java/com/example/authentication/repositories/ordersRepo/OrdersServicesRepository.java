package com.example.authentication.repositories.ordersRepo;

import com.example.authentication.models.orders.Orders;
import com.example.authentication.models.orders.OrdersServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrdersServicesRepository extends JpaRepository<OrdersServices, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from orders_services  " +
            " WHERE order_id=?1 ", nativeQuery = true)
    void deleteAllByOrderId(int orderId);


}
