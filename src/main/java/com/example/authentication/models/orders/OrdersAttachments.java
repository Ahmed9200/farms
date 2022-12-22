package com.example.authentication.models.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders_attachments")
public class OrdersAttachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "added_by")
    private String addedBy; // owner id , or da3ma admin

    @Column(name = "order_attachment")
    private String orderAttachment;

    @Column(name = "date")
    private String date;

    @Lob
    @Column(name = "description")
    private String description;


}
