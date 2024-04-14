package com.apitest.TestSping.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity//(name = "PRODUCT")// để chỉ nó là entity
@Table(name= "staff")
public class Staff {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String nameStaff;

    @Column(name = "birth")
    private Date birth;

    @Column(name = "cmnd")
    private String cmnd;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "image")
    private String image;
}
