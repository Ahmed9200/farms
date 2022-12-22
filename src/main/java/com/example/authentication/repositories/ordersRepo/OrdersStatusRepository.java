package com.example.authentication.repositories.ordersRepo;

import com.example.authentication.models.orders.Orders;
import com.example.authentication.models.orders.OrdersStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersStatusRepository extends JpaRepository<OrdersStatus, Integer> {



}
