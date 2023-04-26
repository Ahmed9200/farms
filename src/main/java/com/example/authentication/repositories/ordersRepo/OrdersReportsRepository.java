package com.example.authentication.repositories.ordersRepo;

import com.example.authentication.models.orders.OrdersReports;
import com.example.authentication.models.orders.OrdersStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersReportsRepository extends JpaRepository<OrdersReports, Integer> {


    List<OrdersReports> findByOrderId(int orderId);

}
