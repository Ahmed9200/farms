package com.example.farms.models.entities;

import com.example.farms.DTO.Add.AddInvoiceDto;
import com.example.farms.models.enums.Constants;
import com.example.farms.models.enums.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username", length = 150)
    private String username;
    @Column(name = "user_phone", length = 50, nullable = false)
    private String userPhone;
    @Column(name = "user_phone2", length = 50)
    private String userPhone2;
    @Lob
    @Column(name = "user_address")
    private String userAddress;
    @Column(name = "total", nullable = false)
    private double total;
    @Column(name = "vat", nullable = false)
    private int vat=14;
    @Column(name = "delivery", nullable = false)
    private int delivery=0;
    @Column(name = "status")
    private InvoiceStatus status = InvoiceStatus.WAITING;
    @Column(name = "date")
    @CreationTimestamp
    private Date date;

    public Invoices(AddInvoiceDto request) {
     vat= Constants.VAT;
     delivery=Constants.DELIVERY;
     userAddress=request.getAddress();
     userPhone=request.getPhone1();
     userPhone2=request.getPhone2();
     username=request.getUsername();
     total = request.getTotal();
    }
}
