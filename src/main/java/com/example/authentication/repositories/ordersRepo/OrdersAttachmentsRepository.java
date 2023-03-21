package com.example.authentication.repositories.ordersRepo;

import com.example.authentication.models.orders.Orders;
import com.example.authentication.models.orders.OrdersAttachments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrdersAttachmentsRepository extends JpaRepository<OrdersAttachments, Integer> {

    List<OrdersAttachments> findAllByOrderId(int orderId);

    @Modifying
    @Transactional
    @Query(value = "delete from orders_attachments  " +
            " WHERE order_id=?1 ", nativeQuery = true)
    void deleteAllByOrderId(int orderId);



}
