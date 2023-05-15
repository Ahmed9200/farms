package com.example.authentication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 450, nullable = false)
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "sale")
    private String sale;

    @Column(name = "is_new_items")
    private boolean isNewItems = false;

    @Column(name = "added_date")
    private Date addedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Categories category;

//    @Column(name = "category_id")
//    private int categoryId;

    @Lob
    @Column(name = "photo")
    private String photo;

}
